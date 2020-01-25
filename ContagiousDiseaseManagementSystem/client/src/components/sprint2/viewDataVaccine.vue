<template>
 <div class="text-center">
  <v-container>
    <v-card class="mx-auto text-center" style="width: 950px;">
      <!-- หัวเรื่อง -->
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">แสดงข้อมูลวัคซีนป้องกัน/ยา</h1>
        </v-flex>
      </v-layout>

      <!-- ค้นหาด้วย vaccine -->
      <v-row >
        <v-spacer></v-spacer>
        <v-col cols ="6">
          <v-select
            v-model="vaccineinformation.vaccineid"
            :items="vaccines"
            item-text="vaccinename"
            item-value="vaccineid"
            :rules="[v => !!v || 'Item is required']"
            label="-- Vaccine --"
            required
          ></v-select>
        </v-col>
        <v-col> 
            <v-btn color="success" height="40" width="100" @click="findbyData">ค้นหา</v-btn>
        </v-col>
      </v-row>
          <!-- แสดงผลข้างเคียงของวัคซีนป้องกัน/ยา -->
      <v-row  v-if="check">
          <v-col cols="11">
            <v-data-table
              :headers="headers"
              :items="vcdata"
              :items-per-page="5"
              class="elevation-1"
            ></v-data-table>
          </v-col>
      </v-row>

      <!-- ปุ่มกลับ -->
      <v-row justify="center">
        <v-col cols="2">
          <v-btn color="success" height="40" width="100" to="/vaccineinformation">กลับ</v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
 </div>
</template>

<script>
import http from "../sprint2/http-common";
export default {
  data() {
    return {
      check: false,
      vaccineid: [
      ],
      vaccines: [],
      vaccinename: '',
      vcdata:[{
            vaccinedata: ""
        }],
      vaccineinformation: [],
      headers: [
        {
          text: "ผลข้างเคียงของวัคซีนป้องกัน/ยา",
          value: "vaccine.vaccinedata"
        }
      ]
    };
  },
  /* eslint-disable no-console */
  methods: {
      findbyData() {
      http
        .get("/vaccine/" + this.vaccineinformation.vaccineid)
        .then(response => {
          this.vaccinedata = response.data.vaccinedata;
          console.log(response);
          if (response.data != null) {
            this.check = true;
          } else {
            alert("ไม่สามารถค้นหาได้");
          }
        })
    },

    getVaccines() {
      http
        .get("/vaccine")
        .then(response => {
          this.vaccines = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    pushvaccineinformation() {
      this.$router.push("/vaccineinformation");
    }
  },
  mounted() {
    this.getVaccines();
  }
};
</script>