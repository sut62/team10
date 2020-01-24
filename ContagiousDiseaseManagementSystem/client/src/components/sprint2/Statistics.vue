<template>
  <div class="text-center">
      <v-card class="mx-auto" style="max-width: 600px">

        <v-layout text-center wrap column>
          <v-toolbar-title class="headline text-uppercase">
            <br />
            <div class="success pa-8 white--text">ระบบเก็บสถิติการเกิดโรคติดต่อ</div>
            <br />
          </v-toolbar-title>
        </v-layout>

        <v-card-text>
            <v-row justify="center">
              <v-col cols="10"> 
                <v-select
                  label="ชื่อโรคติดต่อ"
                  outlined
                  :items="disease"
                  item-text="disease"
                  item-value="id"
                  color="teal"
                  v-model="statistics.disease"
                  :rules="[v => !!v || 'Item is required']"
                  required>
                </v-select>
              </v-col>

              <v-col cols="10">
              <v-select
                label="ประเภทของโรคติดต่อ"
                outlined
                :items="type"
                item-text="type"
                item-value="id"
                color="teal"
                v-model="statistics.type"
                :rules="[v => !!v || 'Item is required']"
                required>
              </v-select>
              </v-col>

              <v-col cols="10">
              <v-select
                label="จังหวัด"
                outlined
                :items="province"
                item-text="province"
                item-value="id"
                color="teal"
                v-model="statistics.province"
                :rules="[v => !!v || 'Item is required']"
                required>
              </v-select>
              </v-col>

              <v-col cols="10">
                <v-text-field
                  label="จำนวนผู้ป่วยโรคติดต่อ"
                  color="teal"
                  v-model="rates"
                  :rules="[v => !!v || 'Item is required']"
                  required>
                </v-text-field>
              </v-col>

              <v-col>
                <v-btn @click="saveStatistics" height="40" width="100" color="success">บันทึก</v-btn>
              </v-col>
              <v-spacer></v-spacer>
              <v-col >
                <v-btn color="success" height="40" width="100" to="/home">กลับ</v-btn>
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
      saveSuccessful: false
    };
  },

  /* eslint-disable no-console */
    /* eslint-disable */
  methods: {
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

    // ดึงข้อมูล combobox
    getProvince() {
      http
        .get("/province")
        .then(response => {
          this.$forceUpdate();
          this.province = response.data;
          console.log(response.data);
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
            this.statistics.province+
            "/" +
            this.rates,
            this.statistics
        )
        .then(response => {
          console.log(response);
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

    clear() {
      this.$refs.form.reset();
      this.statisticsCheck = false;
    },
    refreshList() {
      this.getDisease();
      this.getType();
      this.getProvince();
    }
  },
  mounted() {
    this.getDisease();
    this.getType();
    this.getProvince();
  }
};
</script>
