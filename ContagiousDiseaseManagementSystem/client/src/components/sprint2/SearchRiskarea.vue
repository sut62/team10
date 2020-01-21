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
        <v-col cols="15">
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
        </v-col>
      </v-row>

      <!-- ปุ่มค้นหา -->
      <v-row>
        <v-col>
          <v-btn color="success" height="40" width="100" @click="findbyDisease">ค้นหา</v-btn>
        </v-col>
      </v-row>

      <!-- ตาราง -->
      <div v-if="diseasecheck">
        <v-row></v-row>
      </div>

      <!-- ปุ่มกลับ -->
      <v-row>
        <v-col>
          <v-btn color="success" height="40" width="100" to="/home">กลับ</v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import http from "../sprint2/http-common";
export default {
  data() {
    return {
      riskarea: [
        {
          province: "",
          disease: "",
          communicablelevel: ""
        }
      ],
      province: [],
      disease: [],
      communicablelevel: [],
      diseasecheck: ""
    };
  },

  /* eslint-disable no-console */
  methods: {
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

    findbyDisease() {
        http
            .get("/disease/" + this.riskarea.disease)
            .then(response => {
          console.log(response);
          if (response.data != null) {
            this.diseasecheck = response.data.disease;
          } else {
              alert("ไม่สามารถค้นหาได้");
          }
        })
    }
  },

  mounted() {
    // this.getRiskarea();
    this.getDisease();
    this.getPatient();
    this.getCommunicablelevel();
  }
};
</script>
