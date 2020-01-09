<template>
  <div class="text-center">
    <v-container>
      <v-card class="mx-auto" style="max-width: 950px;">
        
        <!-- หัวเรื่อง -->
        <v-layout text-center wrap>
          <v-flex mb-4>
            <br />
            <h1 class="display-2 font-weight-bold mb-3">จัดเก็บพื้นที่เสี่ยง</h1>
          </v-flex>
        </v-layout>

        <!-- เลือกจังหวัด -->
        <v-row>
          <v-col cols="15">
            <label class="headline">กรุณาเลือกชื่อจังหวัด</label>
            <v-select
              class="mx-auto"
              style="width: 500px"
              label="เลือกจังหวัด"
              outlined
              v-model="riskarea.province"
              :items="province"
              item-text="province"
              item-value="id"
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-select>
          </v-col>

          <!-- เลือกชื่อโรค -->
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
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-select>
          </v-col>

          <!-- เลือกระดับของการระบาด -->
          <v-col cols="15">
            <label class="headline">กรุณาเลือกระดับของการระบาด</label>
            <v-select
              class="mx-auto"
              style="width: 500px"
              label="เลือกระดับของการระบาด"
              outlined
              v-model="riskarea.communicablelevel"
              :items="communicablelevel"
              item-text="communicablelevel"
              item-value="id"
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-select>
          </v-col>
        </v-row>

        <!-- กรอกวันที่บันทึก -->
        <v-row justify="center">
          <v-col cols="12" lg="6">
            <label>วันที่บันทึก:</label>
            <v-menu
              ref="menu1"
              v-model="menu1"
              :close-on-content-click="false"
              transition="scale-transition"
              offset-y
              full-width
              max-width="290px"
              min-width="290px"
            >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="dateFormatted"
                label="กรุณากรอกวันที่"
                @blur="date = parseDate(dateFormatted)"
                v-on="on"
              ></v-text-field>
            </template>
                <v-date-picker v-model="date" no-title @input="menu1 = false"></v-date-picker>
            </v-menu>
          </v-col>
        </v-row>

        <!-- ปุ่มบันทึก -->
        <v-row>
          <v-col cols="15">
            <v-btn color="success" height="50" width="100" @click="saveRiskarea" dark>บันทึก</v-btn>
          </v-col>
        </v-row>

      </v-card>
    </v-container>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {
      province: [],
      disease: [],
      communicablelevel: [],
      date: new Date().toISOString().substr(0, 10),
      dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
      menu1: false,
      riskarea: [
        {
          province: "",
          disease: "",
          communicablelevel: ""
        }
      ]
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
    // ดึงข้อมูลชื่อจังหวัดใส่ combobox
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
    // ดึงข้อมูลชื่อโรคใส่ combobox
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
    // ดึงข้อมูลระดับของการระบาดใส่ combobox
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
    // function เมื่อกดปุ่ม บันทึก
    saveRiskarea() {
      http
        .post(
          "/riskarea/" +
            this.riskarea.province +
            "/" +
            this.riskarea.disease +
            "/" +
            this.riskarea.communicablelevel +
            "/" +
            this.date,
          this.riskarea
        )
        .then(response => {
          console.log(response);
          alert("บันทึกข้อมูลสำเร็จ");
        })
        .catch(e => {
          console.log(e);
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
    this.getProvince();
    this.getDisease();
    this.getCommunicablelevel();
  }
};
</script>
