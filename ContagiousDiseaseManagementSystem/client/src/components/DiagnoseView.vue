<template>
  <v-container>
    <v-card class="mx-auto" style="max-width: 2000px;">
        <v-layout text-center wrap>
            <v-flex mb-4>
                <br />
                <h2 > DiagnoseView </h2>
            </v-flex>
        </v-layout>

        <v-row justify="center">
        <v-col cols="12">
            <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1">
            </v-data-table>
        </v-col>
        </v-row>
    </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "DiagnoseView",
  data() {
    return {
      headers: [
        {
          text: "Patient",
          align: "left",
          sortable: false,
          value: "patientfullname.patientfullname"
        },
        { text: "DiagnosisDoctor", value: "fullname.fullname" },
        { text: "Disease", value: "disease.disease" },
        { text: "Admission", value: "admission.admitted" },
        { text: "Diagnosis Date", value: "diagnosisDate" },
        { text: "Stay Alerted Time", value: "stayAlertedTime" },
        { text: "Diagnosis", value: "diagnosis" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getDiagnoses() {
      http
        .get("/diagnose")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getVideoRentals();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getDiagnoses();
  }
};
</script>