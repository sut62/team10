<template>
  <v-container>
    <v-card class="mx-auto" tile max-width="600">

      <v-layout text-center wrap column>
        <v-toolbar-title class="headline text-uppercase">
          <br />
          <h2>ระบบเก็บข้อมูลโรคติดต่อ</h2>
          <br />
        </v-toolbar-title>
      </v-layout>

      <v-card-text>
        <v-row>
          <v-select
            label="ชื่อโรคติดต่อ"
            outlined
            :items="disease"
            item-text="disease"
            item-value="id"
            style="width: 400px"
            color="teal"
            v-model="contagion.disease"
            :rules="[v => !!v || 'Item is required']"
            required>
          </v-select>
        </v-row>

        <v-row>
          <v-select
            label="ประเภทของโรคติดต่อ"
            outlined
            :items="type"
            item-text="type"
            item-value="id"
            style="width: 400px"
            color="teal"
            v-model="contagion.type"
            :rules="[v => !!v || 'Item is required']"
            required>
          </v-select>
        </v-row>

        <v-row>
          <v-text-field
            label="พาหะ"
            color="teal"
            v-model="carrier"
            style="width: 400px"
            :rules="[v => !!v || 'Item is required']"
            required>
          </v-text-field>
        </v-row>

        <v-row>
          <v-select
            label="อาการของโรคติดต่อ"
            outlined
            :items="symptom"
            item-text="symptom"
            item-value="id"
            style="width: 400px"
            color="teal"
            v-model="contagion.symptom"
            :rules="[v => !!v || 'Item is required']"
            required>
          </v-select>
        </v-row>

        <v-row>
          <v-select
            label="อัตราการติดต่อของโรคติดต่อ"
            outlined
            :items="rate"
            item-text="rate"
            item-value="id"
            style="width: 400px"
            color="teal"
            v-model="contagion.rate"
            :rules="[v => !!v || 'Item is required']"
            required>
          </v-select>
        </v-row>

        <v-row>
          <v-select
            label="การรักษา"
            outlined
            :items="heal"
            item-text="heal"
            item-value="id"
            style="width: 400px"
            color="teal"
            v-model="contagion.heal"
            :rules="[v => !!v || 'Item is required']"
            required>
          </v-select>
        </v-row>

        <v-row>
          <v-col>
            <v-btn @click="saveContagion" height="40" color="success">บันทึก</v-btn>
          </v-col>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-col>
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
  </v-container>
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
        rate: "",
        heal: "", 
  
      },
      menu1: false,
      disease: [],
      type: [],
      carrier: "",
      symptom: [],
      rate: [],
      heal:[],
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

    // ดึงข้อมูล combobox
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

    // ดึงข้อมูล combobox
    getHeal() {
      http
        .get("/heal")
        .then(response => {
          this.$forceUpdate();
          this.heal = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม บันทึก
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
            this.contagion.rate +
            "/" +
            this.contagion.heal,
            this.contagion
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
      this.contagionCheck = false;
    },
    refreshList() {
      this.getDisease();
      this.getType();
      this.getSymptom();
      this.getRate();
      this.getHeal();
    },
  },
  mounted() {
    this.getDisease();
    this.getType();
    this.getSymptom();
    this.getRate();
    this.getHeal();
  }
};
</script>
