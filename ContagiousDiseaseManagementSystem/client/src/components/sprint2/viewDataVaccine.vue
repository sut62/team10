<template>
  <div class="text-center">
    <v-container>
      <v-card class="mx-auto text-center" style="width: 1000px;">
        <!-- หัวเรื่อง -->
        <v-layout text-center wrap>
          <v-flex mb-4>
            <br />
            <h1 class="display-2 font-weight-bold mb-3">แสดงข้อมูลวัคซีนป้องกัน/ยา</h1>
          </v-flex>
        </v-layout>

        <v-row justify="center">
          <v-col cols="5">
            <v-text-field
              v-model="search"
              label="ค้นหา"
              prepend-icon="mdi mdi-file-find"
              outlined
              hide-details
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="15">
            <v-data-table
              :headers="headers"
              :items="vaccines"
              :items-per-page="5"
              :search="search"
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
      search: "",
      vaccines: [
        {
          vaccinedata: ""
        }
      ],
      headers: [
        { text: "ขื่อวัคซีนป้องกัน/ยา", value: "vaccinename" },
        { text: "ผลข้างเคียงของวัคซีนป้องกัน/ยา", value: "vaccinedata" }
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
          }
        });
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