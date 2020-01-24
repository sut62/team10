<template>
    <v-container>
        <v-card class="mx-auto" style="max-width: 600px">
          <div v-if="saveUnsuccessful">
            <v-alert outlined dense text type="error" prominent border="left">
                ข้อมูลไม่ครบถ้วน
            </v-alert>
          </div>

          <div v-if="saveSuccessful">
            <v-alert dense outlined text prominent type="success">บันทึกสำเร็จ</v-alert>
          </div>

            <v-layout  text-center wrap>
                <v-flex mb-4>
                    <br />
                    <h1 class="font-weight-black mb-3">จัดเก็บข้อมูลผู้ป่วยโรคติดต่อ</h1>
                </v-flex>
            </v-layout>

            <v-card-text>
                <v-form>
                  <v-row>
                      <v-col cols="15">
                          <label class="headline">ชื่อ-สกุล</label>
                          <v-text-field label="กรุณากรอกชื่อ-สกุล" v-model="patientfullname" :rules="[(v) => !!v || 'ยังไม่ได้กรอกข้อมูล']"></v-text-field>
                      </v-col>
                  </v-row>

                  <v-row>    
                      <v-col cols="15">
                        <label class="headline">เพศ</label>
                        <v-select label="กรุณาเลือกเพศ"  v-model="patient.gender" :items="gender"
                            class="mx-auto"
                            style="width: 700px"
                            outlined
                            item-text="gender"
                            item-value="id"
                            :rules="[(v) => !!v || 'ยังไม่ได้เลือกข้อมูล']"
                            required>></v-select> 
                      </v-col>
                    </v-row>

                    <v-row>
                      <v-col cols="15">
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
                      <v-col cols="15">
                        <label class="headline">หมู่เลือด</label>
                        <v-select label="กรุณาเลือกหมู่เลือด" v-model="patient.bloodtype" :items="bloodtype"
                            class="mx-auto"
                            style="width: 700px"
                            outlined
                            item-text="bloodtype"
                            item-value="id"
                            :rules="[(v) => !!v || 'ยังไม่ได้เลือกข้อมูล']"
                            required>
                        </v-select>
                      </v-col>
                  </v-row>

                  <v-row>
                      <v-col cols="15">    
                        <label class="headline">เบอร์โทรศัพท์</label>
                        <v-text-field label="กรุณากรอกเบอร์โทรศัพท์" v-model="phone" :rules="[(v) => !!v || 'ยังไม่ได้กรอกข้อมูล']"></v-text-field>
                      </v-col>
                  </v-row>

                  <v-row>
                      <v-col cols="15">
                        <label class="headline">ที่อยู่</label>
                        <v-textarea label="กรุณากรอกที่อยู่" v-model="address" :rules="[(v) => !!v || 'ยังไม่ได้กรอกข้อมูล']"></v-textarea>  
                    </v-col>
                  </v-row>

                  <v-row>
                      <v-col cols="15">
                        <label class="headline">โรคติดต่อที่พบ</label>
                        <v-select label="กรุณาเลือกโรคติดต่อ" :items="disease" v-model="patient.disease"
                            class="mx-auto"
                            style="width: 700px"
                            outlined
                            item-text="disease"
                            item-value="id"
                            :rules="[(v) => !!v || 'ยังไม่ได้เลือกข้อมูล']"
                            required>></v-select>
                      </v-col>
                  </v-row>

                  <v-row>
                      <v-col cols="15">
                          <v-btn  
                            color="success" 
                            height="40" 
                            width="100" 
                            @click="newPatient" dark>บันทึกข้อมูล
                          </v-btn>
                      </v-col>

                      <v-spacer></v-spacer>
                      <v-spacer></v-spacer>
                      <v-spacer></v-spacer>
                      <v-spacer></v-spacer>

                      <v-col cols="15">
                        <v-btn 
                          color="success" 
                          height="40" 
                          width="100"   
                          to="/home">กลับ
                        </v-btn>
                      </v-col>
                  </v-row>
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
      address: "",
      saveUnsuccessful: false,
      saveSuccessful: false
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
          this.saveSuccessful = true;
          this.saveUnsuccessful = false;
        })
        .catch(e => {
          console.log(e);
          this.saveSuccessful = false;
          this.saveUnsuccessful = true;
        });
      this.submitted = true;
    }
  },
  mounted() {
    this.getGender();
    this.getBloodtype();
    this.getDisease();  
  }
};
</script>