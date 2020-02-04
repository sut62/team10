<template>
  <div class ="text-center">
    <v-card class="mx-auto" tile style="width: 800px">

      <v-layout text-center wrap column>
        <v-toolbar-title class="headline text-uppercase">
          <br />
          <div class="success pa-7 white--text"><h1>ระบบเก็บสถิติการเกิดโรคติดต่อ</h1></div>
          <br />
        </v-toolbar-title>
      </v-layout>

    <v-card-text>
      <v-row>
        <v-row justify="center">
          <v-col cols="5"> 
            <v-select
              label="ชื่อโรคติดต่อ"
              outlined
              :items="disease"
              item-text="disease"
              item-value="id"
              color="teal"
              v-model="statistics.disease"
              :rules="[v => !!v || 'Item is required']"
              clearable
              required>
            </v-select>
          </v-col>
      
          <v-col cols="5">
            <v-select
              label="ประเภทของโรคติดต่อ"
              outlined
              :items="type"
              item-text="type"
              item-value="id"
              color="teal"
              v-model="statistics.type"
              :rules="[v => !!v || 'Item is required']"
              clearable
              required>
            </v-select>
          </v-col>
        </v-row>
      </v-row>
        
      <v-row>
        <v-row justify="center">
          <v-col cols="5">
            <v-select
              label="จังหวัด"
              outlined
              :items="province"
              item-text="province"
              item-value="id"
              color="teal"
              v-model="statistics.province"
              :rules="[v => !!v || 'Item is required']"
              clearable
              required>
            </v-select>
          </v-col>
      
          <v-col cols="5">
            <v-text-field
              label="จำนวนผู้ป่วยโรคติดต่อ"
              color="teal"
              v-model="rates"
              :rules="[v => !!v || 'Item is required']"
              clearable
              required>
            </v-text-field>
          </v-col>
        </v-row>
      </v-row>
      
      <v-row justify="center">
        <v-col>
          <v-btn @click="saveStatistics" height="40" width="100" color="success" >บันทึก</v-btn>         
        </v-col>
        <v-spacer></v-spacer>
        <v-col>
          <v-btn color="success" height="40" width="100" to="/home">
            <v-icon dark left >mdi-arrow-left</v-icon>กลับ
          </v-btn>
        </v-col>
      </v-row>

      <div v-if="saveUnsuccessful">
        <v-alert outlined dense text type="error" prominent border="left">
          บันทึกข้อมูลไม่สำเร็จ
        </v-alert>
      </div>
      <div v-if="saveSuccessful">
        <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ</v-alert>
      </div>
      
      <!-- ตารางเเสดงข้อมูลการบันทึก -->
      
        <v-layout text-center column>
          <v-toolbar-title class="headline text-uppercase">
            <br />
            <div class="brown lighten-1 lighten-1 pa-1 white--text">
              ตารางเเสดงข้อมูลสถิติการโรคติดต่อ</div>
          </v-toolbar-title>
        </v-layout>

        <v-row>
          <v-col cols="50">
            <v-data-table 
                :headers="headers" 
                :items="items" 
                :items-per-page="5" 
                class="elevation-1">
            </v-data-table>
          </v-col>
        </v-row>

      </v-card-text>
    </v-card>  
  </div>
</template>


<script>
import http from "../sprint2/http-common";
export default {
  name: "statistics",
  data() {
    return {
      statistics: {
        disease: "",
        type: "",
        province: "", 

      },
      menu1: false,
      disease: [],
      type: [],
      province: [],
      rates: "",
      saveUnsuccessful: false,
      saveSuccessful: false,

      headers: [
        { text: "Disease",value: "disease.disease"},
        { text: "Type",value: "type.type"},
        { text: "Province",value: "province.province" },
        { text: "Rates", value: "rates"},
      ],
      items: []
    };
  },

  /* eslint-disable no-console */
   /* eslint-disable */
  methods: {
    getStatistics() {
      http
        .get("/statistics")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล combobox
    getDisease() {
      http
        .get("/disease")
        .then(response => {
          console.log(response.data);
          this.$forceUpdate();
          this.disease = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล combobox
    getType() {
      http
        .get("/type")
        .then(response => {
          console.log(response.data);
          this.$forceUpdate();
          this.type = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },

    getProvince() {
      http
        .get("/province")
        .then(response => {
          console.log(response.data);
          this.$forceUpdate();
          this.province = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม บันทึก
    saveStatistics() {
      http
        .post(
          "/statistics/" +
            this.statistics.disease +
            "/" +
            this.statistics.type +
            "/" +
            this.statistics.province +
            "/" +
            this.rates,
            this.statistics
        )
        .then(response => {
          console.log(response);
          this.getStatistics();
          this.saveSuccessful = true;
          this.saveUnsuccessful = false;
        
        })
        .catch(e => {
          console.log(e);
          this.saveSuccessful = false;
          this.saveUnsuccessful = true;
        });
      this.submitted = true;
    },

    refreshList() {
      this.getStatistics();
      this.getDisease();
      this.getType();
      this.getProvince();
    },
  },
  mounted() {
    this.getStatistics();
    this.getDisease();
    this.getType();
    this.getProvince();
  }
};
</script>
