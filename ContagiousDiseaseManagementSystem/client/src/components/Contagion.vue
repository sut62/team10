<template>
  <div class ="text-center">
    <v-card class="mx-auto" tile style="width: 600px">

      <v-layout text-center wrap column>
        <v-toolbar-title class="headline text-uppercase">
          <br />
          <div class="success pa-8 white--text">ระบบเก็บข้อมูลโรคติดต่อ</div>
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
            v-model="contagion.disease"
            :rules="[v => !!v || 'Item is required']"
            clearable
            required>
          </v-select>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="10">
          <v-select
            label="ประเภทของโรคติดต่อ"
            outlined
            :items="type"
            item-text="type"
            item-value="id"
            color="teal"
            v-model="contagion.type"
            :rules="[v => !!v || 'Item is required']"
            clearable
            required>
          </v-select>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="10">
          <v-text-field
            label="พาหะ"
            color="teal"
            v-model="carrier"
            :rules="[v => !!v || 'Item is required']"
            clearable
            required>
          </v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="10">
          <v-select
            label="อาการของโรคติดต่อ"
            outlined
            :items="symptom"
            item-text="symptom"
            item-value="id"
            color="teal"
            v-model="contagion.symptom"
            :rules="[v => !!v || 'Item is required']"
            clearable
            required>
          </v-select>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="10">
          <v-select
            label="อัตราการติดต่อของโรคติดต่อ"
            outlined
            :items="rate"
            item-text="rate"
            item-value="id"
            color="teal"
            v-model="contagion.rate"
            :rules="[v => !!v || 'Item is required']"
            clearable
            required>
          </v-select>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="10">
          <v-select
            label="การรักษา"
            outlined
            :items="heal"
            item-text="heal"
            item-value="id"
            color="teal"
            v-model="contagion.heal"
            :rules="[v => !!v || 'Item is required']"
            clearable
            required>
          </v-select>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col>
          <v-btn @click="saveContagion" height="40" width="90" color="success">บันทึก</v-btn>
        </v-col>
        <v-spacer></v-spacer>
        <v-col>
          <v-btn color="success" height="40" width="100" to="/home">
            <v-icon dark left >mdi-arrow-left</v-icon>กลับ
          </v-btn>
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
