<template>
  <v-card class="mx-auto" tile max-width="600">
    <v-form>
      <v-layout wrap column>
        <v-toolbar-title class="headline text-uppercase">
          <br />
          <h2>ระบบเก็บข้อมูลโรคติดต่อ</h2>
          <br />
        </v-toolbar-title>

        <v-select
          label="ชื่อโรคติดต่อ"
          outlined
          :items="disease"
          item-text="disease"
          item-value="id"
          style="width: 450px"
          color="teal"
          v-model="contagion.disease"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-select>

        <v-select
          label="ประเภทของโรคติดต่อ"
          outlined
          :items="type"
          item-text="type"
          item-value="id"
          style="width: 450px"
          color="teal"
          v-model="contagion.type"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-select>

        <v-text-field
          label="พาหะ"
          color="teal"
          v-model="carrier"
          style="width: 450px"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-text-field>

        <v-select
          label="อาการของโรคติดต่อ"
          outlined
          :items="symptom"
          item-text="symptom"
          item-value="id"
          style="width: 450px"
          color="teal"
          v-model="contagion.symptom"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-select>

        <v-select
          label="อัตราการติดต่อของโรคติดต่อ"
          outlined
          :items="rate"
          item-text="rate"
          item-value="id"
          style="width: 450px"
          color="teal"
          v-model="contagion.rate"
          :rules="[v => !!v || 'Item is required']"
          required
        ></v-select>
      </v-layout>
      <v-btn @click="saveContagion" color="success">บันทึก</v-btn>
    </v-form>
  </v-card>
</template>

<script>
import http from "../http-common";
export default {
  name: "contagion",
  data() {
    return {
      contagion: {
        disease: "",
        type: "",
        symptom: "",
        rate: ""
      },

      disease: [],
      type: [],
      carrier: "",
      symptom: [],
      rate: []
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

    getSymptom() {
      http
        .get("/symptom")
        .then(response => {
          this.$forceUpdate();
          this.symptom = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getRate() {
      http
        .get("/rate")
        .then(response => {
          this.$forceUpdate();
          this.rate = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveContagion() {
      http
        .post(
          "/contagion/" +
            this.contagion.disease +
            "/" +
            this.contagion.type +
            "/" +
            this.carrier +
            "/" +
            this.contagion.symptom +
            "/" +
            this.contagion.rate,
          this.contagion
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
      this.getSymptom();
      this.getRate();
    }
  },
  mounted() {
    this.getDisease();
    this.getType();
    this.getSymptom();
    this.getRate();
  }
};
</script>
