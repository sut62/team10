<template>
  <v-container>
    <v-card class="mx-auto text-center" style="width: 950px;">
      <!-- หัวเรื่อง -->
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">ค้นหาพื้นที่เสี่ยง</h1>
        </v-flex>
      </v-layout>

      <!-- ค้นหาด้วย Disease -->
      <v-row>
        <v-col>
          <label class="headline">กรุณาเลือกชื่อโรค</label>
          <v-select
            class="mx-auto"
            style="width: 500px"
            label="เลือกชื่อโรค"
            outlined
            v-model="riskarea.disease"
            :items="disease"
            item-text="disease"
            item-value="id"
            :rules="[v => !!v || 'Item is required']"
            required
          ></v-select>
          <!-- search Button -->
          <v-btn 
            color="primary" 
            height="50"
            width="100" 
            @click="findbyDisease" dark>ค้นหา
            <v-icon dark right>mdi-magnify</v-icon>
          </v-btn>
        </v-col>
      </v-row>

      <!-- Alert if data length is null -->
      <v-row justify="center">
        <v-alert style="max-width: 400px;" v-if="saveUnsuccessful" outlined dense text type="error" prominent border="left">ไม่พบข้อมูล</v-alert>
      </v-row>
      
      <!-- Show Data -->
      <div v-if="diseasecheck">
        <v-row justify="center">
          <v-alert style="max-width: 400px;" v-if="saveSuccessful" dense outlined text prominent type="success">ค้นหาสำเร็จ</v-alert>
        </v-row>
        <!-- ตาราง -->
        <v-data-table
          :headers="headers"
          :items="riskareadata"
          :items-per-page="5"
          class="elevation-1 mx-auto text-center"
          style="width: 850px;"
        ></v-data-table>

        <!-- ปุ่มกลับ -->
        <v-row>
          <v-col>
            <v-btn 
              class="ma-5"
              color="grey lighten-2" 
              height="50" 
              width="80"   
              to="/home">
              <v-icon dark left>mdi-arrow-left</v-icon>
              กลับ
            </v-btn>
          </v-col>
        </v-row>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import http from "../sprint2/http-common";
export default {
  data() {
    return {
      disease: [],
      riskarea: [],
      date: new Date().toISOString().substr(0, 10),
      dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
      riskareadata: [{
        province: '',
        communicablelevel: '',
        date: ''
      }],
      diseasecheck: false,
      saveSuccessful: false,
      saveUnsuccessful: false,
      headers: [
        { text: 'จังหวัด', value: 'province.province'},
        { text: 'ระดับของการระบาด', value: 'communicablelevel.communicablelevel'},
        { text: 'วันที่บันทึก', value: 'date'},
      ],
    };
  },

  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    }
  },

  watch: {
    date() {
      this.dateFormatted = this.formatDate(this.date);
    }
  },

  /* eslint-disable no-console */
  methods: {
    getRiskarea() {
      http
        .get("/riskarea")
        .then(response => {
          this.riskarea = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getDisease() {
      http
        .get("/disease")
        .then(response => {
          this.disease = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getProvince() {
      http
        .get("/province")
        .then(response => {
          this.province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getCommunicablelevel() {
      http
        .get("/communicablelevel")
        .then(response => {
          this.communicablelevel = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    findbyDisease() {
      http
        .get("/riskarea/" + this.riskarea.disease)
        .then(response => {
          this.riskareadata = response.data;
          this.diseasecheck = response.status;
          console.log(response);
          if (response.data.length == 0) {
            this.diseasecheck = false;
            this.saveSuccessful = false;
            this.saveUnsuccessful = true;
          }
          else if (response.data) {
            this.saveSuccessful = true;
            this.saveUnsuccessful = false;
          }
        })
        .catch(e => {
          console.log(e);
          this.saveSuccessful = false;
          this.saveUnsuccessful = true;
        });
    },
    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;

      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    }
  },
  mounted() {
    this.getRiskarea();
    this.getDisease();
  }
};
</script>
