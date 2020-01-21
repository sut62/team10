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
          <!-- search Button By Disease-->
          <v-btn color="success" height="40" width="100" @click="findbyDisease">ค้นหา</v-btn>
        </v-col>
      </v-row>

      <!-- ตาราง -->
      <div v-if="diseasecheck">
        <h1 class="display-2 font-weight-bold mb-3">Find Success</h1>
        <v-data-table
          :headers="headers"
          :items="riskareadata"
          :items-per-page="5"
          class="elevation-1"
        ></v-data-table>
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
      disease: [],
      riskarea: [],
      riskareadata: [{
        province: '',
        communicablelevel: '',
        date: ''
      }],
      diseasecheck: false,
      headers: [
        { text: 'จังหวัด', value: 'province.province'},
        { text: 'ระดับของการระบาด', value: 'communicablelevel.communicablelevel'},
        { text: 'วันที่บันทึก', value: 'date'},
      ],
    };
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
          console.log(response);
          if (response.data != null) {
            this.diseasecheck = true;
          } else {
            alert("ไม่สามารถค้นหาได้");
          }
        })
    }
  },

  mounted() {
    this.getRiskarea();
    this.getDisease();
  }
};
</script>
