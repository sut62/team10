<template>
  <v-container>
    <v-card class="mx-auto" style="max-width: 950px;">
      <div v-if="saveUnsuccessful">
        <v-alert outlined dense text type="error" prominent border="left">
            <strong>บันทึกไม่สำเร็จ</strong>
            กรุณากรอกข้อมูลให้ครบและถูกต้องก่อนบันทึกข้อมูล
        </v-alert>
      </div>

      <div v-if="saveSuccessful">
        <v-alert dense outlined text prominent type="success">บันทึกสำเร็จ</v-alert>
      </div>

      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">วินิจฉัยโรคติดต่อ</h1>
        </v-flex>
      </v-layout>

      <v-row justify="center">
        <v-col>
          <v-form v-model="valid" ref="form">
            <v-row justify="center">
              <v-col cols="10">
                <v-text-field
                  full-width
                  max-width="550px"
                  min-width="550px"
                  outlined
                  label="Patient Fullname"
                  v-model="patientPhone"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
                <p v-if="patientCheck != ''">Patient Phone : {{patientPhone}}</p>
              </v-col>
              <v-col>
                <div class="my-2">
                  <v-btn @click="findPatient" depressed large color="primary">ค้นหา</v-btn>
                </div>
              </v-col>
            </v-row>

            <div v-if="patientCheck">
              <v-row>
                <v-col>
                  <v-select
                    full-width
                    max-width="550px"
                    min-width="550px"
                    label="MedicalStaff"
                    outlined
                    v-model="diagnose.medicalStaffId"
                    :items="medicalStaffWherePositionIsDoctors"
                    item-text="fullname"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-select
                    full-width
                    max-width="550px"
                    min-width="550px"
                    label="Disease"
                    outlined
                    v-model="diagnose.diseaseId"
                    :items="diseases"
                    item-text="disease"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-select
                    full-width
                    max-width="550px"
                    min-width="550px"
                    label="Admission"
                    outlined
                    v-model="diagnose.admissionId"
                    :items="admissions"
                    item-text="admitted"
                    item-value="id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-text-field
                    full-width
                    max-width="550px"
                    min-width="550px"
                    label="Diagnosis"
                    v-model="diagnose.diagnosis"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-text-field
                    full-width
                    max-width="550px"
                    min-width="550px"
                    label="stayAlertedTime"
                    v-model="diagnose.stayAlertedTime"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row justify="center">
                <v-col>
                  <v-btn @click="saveDiagnose" :class="{ red: !valid, green: valid }">บันทึกการวินิจฉัย</v-btn>
                  <v-btn style="margin-left: 30px;" @click="clear">เคลียร์</v-btn>
                  <v-btn 
                      color="success" 
                      height="40" 
                      width="50"   
                      to="/">กลับ
                  </v-btn>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="10">
                  <v-btn
                      style="margin-right : 100px"
                      color="primary" 
                      height="40" 
                      width="200"   
                      to="/diagnoseView"> ไปหน้าแสดงผลการวินิจฉัย
                  </v-btn>
                </v-col>
                <v-col>
                  <v-btn 
                      color="primary" 
                      height="40" 
                      width="100"   
                      to="/home"> ไปหน้าหลัก
                  </v-btn>
                </v-col>
              </v-row>
              <br />
            </div>
          </v-form>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "diagnose",
  
  data() {
    return {
      diagnose: {
        patientId: "",
        medicalStaffId: "",
        diseaseId: "",
        admissionId: "",
        diagnosis: "",
        stayAlertedTime: ""
      },
      valid: false,
      patientCheck: false,
      patientPhone: "",
      saveUnsuccessful: false,
      saveSuccessful: false
    };
  },

  methods: {
    /* eslint-disable no-console */
    getMedicalStaffs() {
      http
        .get("/medicalStaff")
        .then(response => {
          this.medicalStaffs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    getDiseases() {
      http
        .get("/disease")
        .then(response => {
          this.diseases = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    getAdmissions() {
      http
        .get("/admission")
        .then(response => {
          this.admissions = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getMedicalStaffWherePositionIsDoctors() {
      http
        .get("/medicalStaffWherePositionIsDoctor")
        .then(response => {
          this.medicalStaffWherePositionIsDoctors = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    findPatient() {
      http
        .get("/patientByPhone/" + this.patientPhone)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.diagnose.patientId = response.data.id;
            if(this.patientPhone == response.data.phone){
              this.patientCheck = true;
            }
            
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    
    saveDiagnose() {
      http
        .post(
          "/diagnose/" +
            this.diagnose.patientId +
            "/" +
            this.diagnose.medicalStaffId +
            "/" +
            this.diagnose.diseaseId +
            "/" +
            this.diagnose.admissionId +
            "/" +
            this.diagnose.diagnosis +
            "/" +
            this.diagnose.stayAlertedTime,
          this.diagnose
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
      this.patientCheck = false;
      this.saveSuccessful = false;
      this.saveUnsuccessful = false;
    },
    refreshList() {
      this.getMedicalStaffs();
      this.getDiseases();
      this.getAdmissions();
      this.getMedicalStaffWherePositionIsDoctors();
    }
  },

  mounted() {
    /* eslint-enable no-console */
    this.getMedicalStaffs();
    this.getDiseases();
    this.getAdmissions();
    this.getMedicalStaffWherePositionIsDoctors();
  }
};
</script>