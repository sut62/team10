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
                  label="Patient Find By Person ID"
                  v-model="personId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
                <p v-if="patientCheck != ''">Person Id : {{personId}}</p>
              </v-col>
              <v-col>
                <div class="my-2">
                  <v-btn @click="findPatient" depressed large color="primary">ค้นหา</v-btn>
                </div>
              </v-col>
            </v-row>

            <div v-if = "patientCheck">
              <v-row>
                <v-col>
                  <v-select
                    full-width
                    max-width="550px"
                    min-width="550px"
                    label="DiagnosisDoctor"
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
                    label="Blood Pressure Level"
                    outlined
                    v-model="diagnose.bloodPressureLevelId"
                    :items="bloodPressureLevels"
                    item-text="level"
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
                  <v-select
                  full-width max-width="550px"
                  min-width="550px" label="Diseases"
                  outlined :items="diseases"
                  v-model="diagnose.diseaseIds"
                  item-text="disease"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required multiple>
                    <template v-slot:prepend-item>
                      <v-list-item ripple @click="toggle">
                        <v-list-item-action>
                          <v-icon :color="diagnose.diseaseIds.length > 0 ? 'indigo darken-4' : ''">{{icon}}</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                          <v-list-item-title>Select All Diseases</v-list-item-title>
                       </v-list-item-content>
                      </v-list-item>
                      <v-divider class="mt-2"></v-divider>
                    </template>
                        
                    <template v-slot:append-item>
                      <v-divider class="mb-2"></v-divider>
                        <v-list-item disabled>
                          <v-list-item-avatar color="grey lighten-3">
                            <v-icon>mdi-food-apple</v-icon>
                          </v-list-item-avatar>

                          <v-list-item-content v-if="selectAllDiseases">
                            <v-list-item-title>You select all diseases!</v-list-item-title>
                          </v-list-item-content>

                          <v-list-item-content v-else-if="selectSomeDiseases">
                            <v-list-item-title>Disease Count</v-list-item-title>
                            <v-list-item-subtitle>{{diagnose.diseaseIds.length}}</v-list-item-subtitle>
                          </v-list-item-content>

                          <v-list-item-content v-else>
                            <v-list-item-title>You are not yet select disease?</v-list-item-title>
                            <v-list-item-subtitle>Please select at least one disease!</v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                    </template>
                  </v-select>
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
        patientId: null,
        medicalStaffId: null,
        bloodPressureLevelId: null,
        admissionId: null,
        diagnosis: null,
        stayAlertedTime: null,
        diseaseIds: []
      },
      valid: false,
      patientCheck: false,
      personId: null,
      saveUnsuccessful: false,
      saveSuccessful: false
    };
  },

  computed: {
    selectAllDiseases () {
      return this.diagnose.diseaseIds.length === this.diseases.length
    },
    selectSomeDiseases () {
      return this.diagnose.diseaseIds.length > 0 && !this.selectAllDiseases
    },
    icon () {
      if (this.selectAllDiseases) return 'mdi-close-box'
      if (this.selectSomeDiseases) return 'mdi-minus-box'
      return 'mdi-checkbox-blank-outline'
    },
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

    getBloodPressureLevels() {
      http
        .get("/bloodPressureLevel")
        .then(response => {
          this.bloodPressureLevels = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    findPatient() {
      http
        .get("/patientByPersonId/" + this.personId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.diagnose.patientId = response.data.id;
            if(this.personId == response.data.personId){
              this.patientCheck = true;
            } else {
              this.patientCheck = false;
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
            this.diagnose.bloodPressureLevelId +
            "/" +
            this.diagnose.admissionId +
            "/" +
            this.diagnose.diagnosis +
            "/" +
            this.diagnose.stayAlertedTime +
            "/" +
            this.diagnose.diseaseIds,
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
      this.getBloodPressureLevels();
      this.getAdmissions();
      this.getDiseases();
      this.getMedicalStaffWherePositionIsDoctors();
    },
    toggle () {
      this.$nextTick(() => {
        if (this.selectAllDiseases) {
          this.diagnose.diseaseIds = []
        } else {
          this.diagnose.diseaseIds = this.diseases.slice()
        }
      })
    }
  },

  mounted() {
    /* eslint-enable no-console */
    this.getMedicalStaffs();
    this.getBloodPressureLevels();
    this.getAdmissions();
    this.getDiseases();
    this.getMedicalStaffWherePositionIsDoctors();
  }
};
</script>