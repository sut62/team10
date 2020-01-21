<template>
  <v-card class="mx-auto" tile max-width="500">
    <v-form>
      <v-layout wrap column>
        <v-toolbar-title class="headline text-uppercase">
          <br />
          <h2>ระบบเก็บสถิติการเกิดโรคติดต่อ</h2>
          <br />
        </v-toolbar-title>

        <v-select
          label="ชื่อโรคติดต่อ"
          outlined
          :items="disease"
          item-text="disease"
          item-value="id"
          style="width: 500px"
          color="teal"
          v-model="statistics.disease"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-select>

        <v-select
          label="ประเภทของโรคติดต่อ"
          outlined
          :items="type"
          item-text="type"
          item-value="id"
          style="width: 500px"
          color="teal"
          v-model="statistics.type"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-select>

        <v-select
          label="จังหวัด"
          outlined
          :items="province"
          item-text="province"
          item-value="id"
          style="width: 500px"
          color="teal"
          v-model="statistics.province"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-select>

         <v-text-field
          label="จำนวน"
          color="teal"
          v-model="rates"
          style="width: 500px"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-text-field>
      </v-layout>
    <v-row>     
      <v-col>
        <v-btn @click="saveStatistics" color="success">บันทึก</v-btn>
      </v-col>
      <v-spacer></v-spacer>
      <v-col>
        <v-btn color="success" height="40" width="100" to="/">กลับ</v-btn>
      </v-col>
    </v-row>
    </v-form>
  </v-card>
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
        province: ""
      },

      disease: [],
      type: [],
      province: [],
      rates: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
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
          this.$forceUpdate();
          this.province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

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
          alert("บันทึกข้อมูลสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("บันทึกข้อมูลไม่สำเร็จ");
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
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
