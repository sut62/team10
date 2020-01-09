<template>
  <v-container>
    <v-card class="mx-auto" style="max-width: 950px;">
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
                  label="Patient ID"
                  v-model="diagnose.patientId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
                <p v-if="patientCheck != ''">Patient Id : {{patientId}}</p>
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
                    item-text="id"
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
                    v-model="diagnose.admissionAdmitted"
                    :items="admissions"
                    item-text="admitted"
                    item-value="admitted"
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
        admissionAdmitted: "",
        diagnosis: "",
        stayAlertedTime: ""
      },
      valid: false,
      patientCheck: false,
      patientId: ""
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

    getMedicalStaffWherePositionisDoctors() {
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
        .get("/patient/" + this.diagnose.patientId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.patientId = response.data.id;
            this.patientCheck = response.status;
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
            this.diagnose.admissionAdmitted +
            "/" +
            this.diagnose.diagnosis +
            "/" +
            this.diagnose.stayAlertedTime,
          this.diagnose
        )
        .then(response => {
          console.log(response);
          //this.$router.push("");
          alert("บันทึกสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("บันทึกไม่สำเร็จ");
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.patientCheck = false;
    },
    refreshList() {
      this.getMedicalStaffs();
      this.getDiseases();
      this.getAdmissions();
      this.getMedicalStaffWherePositionisDoctors();
    }
  },

  mounted() {
    /* eslint-enable no-console */
    this.getMedicalStaffs();
    this.getDiseases();
    this.getAdmissions();
    this.getMedicalStaffWherePositionisDoctors();
  }
};
</script>