<template>
    <v-container>
        <v-card class="mx-auto" Patient="24" tile max-width="600">
            <v-card-title class="display-1">จัดเก็บข้อมูลผู้ป่วยโรคติดต่อ</v-card-title>
            <v-card-text>
                <v-form>
                    <v-layout wrap column>
                    <v-row>
                        <v-col cols="12" md="6">
                            <label class="headline">ชื่อ-สกุล</label>
                            <v-text-field label="กรุณากรอกชื่อ-สกุล" v-model="patientfullname" :rules="[(v) => !!v || 'ยังไม่ได้กรอกข้อมูล']"></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col>
                            <v-flex xs12 md4>
                                <label class="headline">เพศ</label>
                                <v-select label="กรุณาเลือกเพศ"  v-model="patient.gender" :items="gender"
                                    class="mx-auto"
                                    style="width: 500px"
                                    outlined
                                    item-text="gender"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'ยังไม่ได้เลือกข้อมูล']"
                                    required>></v-select>
                            </v-flex>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="12" lg="6">
                            <label class="headline">วัน/เดือน/ปีเกิด</label>
                            <v-menu
                                ref="menu1"
                                v-model="menu1"
                                :close-on-content-click="false"
                                transition="scale-transition"
                                offset-y
                                max-width="290px"
                                min-width="290px"
                            >
                            <template v-slot:activator="{ on }">
                                <v-text-field v-model="dateFormatted" label="กรุณากรอกวันที่" @blur="birthdate = parseDate(dateFormatted)" v-on="on"></v-text-field>
                            </template>
                                <v-date-picker v-model="birthdate" no-title @input="menu1 = false"></v-date-picker>
                            </v-menu>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col>
                            <v-flex xs12 md4>
                                <label class="headline">หมู่เลือด</label>
                                <v-select label="กรุณาเลือกหมู่เลือด" v-model="patient.bloodtype" :items="bloodtype"
                                    class="mx-auto"
                                    style="width: 500px"
                                    outlined
                                    item-text="bloodtype"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'ยังไม่ได้เลือกข้อมูล']"
                                    required>
                                </v-select>
                            </v-flex>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col>
                            <v-flex xs12 md4>
                                <label class="headline">เบอร์โทรศัพท์</label>
                                <v-text-field label="กรุณากรอกเบอร์โทรศัพท์" v-model="phone" :rules="[(v) => !!v || 'ยังไม่ได้กรอกข้อมูล']"></v-text-field>
                            </v-flex>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col>
                            <v-flex xs12 md4>
                                <label class="headline">ที่อยู่</label>
                                <v-textarea label="กรุณากรอกที่อยู่" v-model="address" :rules="[(v) => !!v || 'ยังไม่ได้กรอกข้อมูล']"></v-textarea>
                            </v-flex>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col>
                            <v-flex xs12 md4>
                                <label class="headline">โรคติดต่อที่พบ</label>
                                <v-select label="กรุณาเลือกโรคติดต่อ" :items="disease" v-model="patient.disease"
                                    class="mx-auto"
                                    style="width: 500px"
                                    outlined
                                    item-text="disease"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'ยังไม่ได้เลือกข้อมูล']"
                                    required>></v-select>
                            </v-flex>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="15">
                            <v-btn  
                              color="success" 
                              height="50" 
                              width="100" 
                              @click="newPatient" dark>บันทึกข้อมูล
                            </v-btn>
                        </v-col>
                    </v-row>
                    </v-layout>
                </v-form>
            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {
      gender: [],
      bloodtype: [],
      disease: [],
      birthdate: new Date().toISOString().substr(0, 10),
      dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
      menu1: false,
      patient: [
        {
          gender: "",
          bloodtype: "",
          disease: ""
        }
      ],
      patientfullname: "",
      phone: "",
      address: ""
    };
  },

  computed: {
    computedDateFormatted() {
      return this.formatDate(this.birthdate);
    }
  },

  watch: {
    birthdate() {
      this.dateFormatted = this.formatDate(this.birthdate);
    }
  },
  /* eslint-disable no-console */
  methods: {
    // ดึงข้อมูลเพศใส่ combobox
    getGender() {
      http
        .get("/gender")
        .then(response => {
          this.gender = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    formatDate(birthdate) {
      if (!birthdate) return null;
      const [year, month, day] = birthdate.split("-");
      return `${day}/${month}/${year}`;
    },
    parseDate(birthdate) {
      if (!birthdate) return null;
      const [month, day, year] = birthdate.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    },
    // ดึงข้อมูลหมู่เลือดใส่ combobox
    getBloodtype() {
      http
        .get("/bloodtype")
        .then(response => {
          this.bloodtype = response.data;
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
    // function เมื่อกดปุ่ม บันทึก
    newPatient() {
      http
        .post(
          "/patient/" +
            this.patientfullname +
            "/" +
            this.patient.gender +
            "/" +
            this.birthdate +
            "/" +
            this.patient.bloodtype +
            "/" +
            this.phone +
            "/" +
            this.address +
            "/" +
            this.patient.disease,
          this.patient
        )
        .then(response => {
          console.log(response);
          alert("บันทึกข้อมูลสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("ข้อมูลไม่ครบถ้วน");
        });
    }
  },
  mounted() {
    this.getGender();
    this.getBloodtype();
    this.getDisease();  
  }
};
</script>