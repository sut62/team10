<template>
  <v-container>
    <v-card class="mx-auto" style="max-width: 2000px;">
        <v-layout text-center wrap>
            <v-flex mb-4>
                <br />
                <h2 > DiagnoseView </h2>
            </v-flex>
        </v-layout>

        <v-row>
        <v-col cols="12">
            <v-data-table :headers="diagnoseHeaders" :items="diagnoseItems" :items-per-page="10" class="elevation-1">
            </v-data-table>
        </v-col>
        </v-row>

        <v-row>
        <v-col cols="12">
            <v-data-table :headers="diagnoseDiseaseHeaders" :items="diagnoseDiseaseItems" :items-per-page="20" class="elevation-1">
            </v-data-table>
        </v-col>
        </v-row>


        <v-row>
          <v-col cols="4">
            <v-btn 
              color="primary" 
              height="40" 
              width="80"   
              to="/home"> ไปหน้าหลัก
            </v-btn>
          </v-col>
          <v-col cols="4">
            <v-btn 
              color="secondary" 
              height="40" 
              width="300"   
              to="/diagnose"> ไปหน้าวินิจฉัยโรคติดต่อ
            </v-btn>
          </v-col>
        </v-row>
    </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "DiagnoseView",
  data: ()  => ({
    diagnoseItems: [],
    diagnoseHeaders: [
      {
        text: "Patient",
        align: "left",
        sortable: false,
        value: "patient.personId"
      },
      { text: "Diagnose Code", value: "diagnoseCode" },
      { text: "DiagnosisDoctor", value: "diagnosisDoctor.fullname" },
      { text: "Blood Pressure Level", value: "bloodPressureLevel.level" },
      { text: "Admission", value: "admission.admitted" },
      { text: "Diagnosis Date", value: "diagnosisDate" },
      { text: "Stay Alerted Time", value: "stayAlertedTime" },
      { text: "Diagnosis", value: "diagnosis" }
    ],
    diagnoseDiseaseHeaders: [
      {
        text: "Diagnose Code",
        align: "left",
        sortable: true,
        value: "diagnose.diagnoseCode"
      },
      { text: "Disease Per Diagnose", value: "disease.disease" }
    ],
    diagnoseDiseaseItems: []
  }),
  
  methods: {
    /* eslint-disable no-console */
    getDiagnoses() {
      http
        .get("/diagnose")
        .then(response => {
          this.diagnoseItems = response.data;
          console.log(this.diagnoseItems);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getDiagnoseDiseases() {
      http
        .get("/diagnoseDisease")
        .then(response => {
          this.diagnoseDiseaseItems = response.data;
          console.log(this.diagnoseDiseaseItems);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getDiagnoses();
      this.getDiagnoseDiseases();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getDiagnoses();
    this.getDiagnoseDiseases();
  }
};
</script>