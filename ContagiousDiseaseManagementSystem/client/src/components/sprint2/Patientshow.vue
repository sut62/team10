<template>
    <v-container>
        <v-card class="mx-auto text-center" style="max-width: 1500px">
            <v-form>
                <v-layout  text-center wrap>
                    <v-flex mb-4>
                        <br />
                        <h1 class="font-weight-black mb-3">แสดงข้อมูลผู้ป่วยโรคติดต่อ</h1>
                    </v-flex>
                </v-layout>

                <v-row>
                    <v-col cols="12">
                        <v-data-table 
                            :headers="headers" 
                            :items="items" 
                            :items-per-page="5" 
                            class="elevation-1">
                        </v-data-table>
                    </v-col>
                </v-row>
                <v-spacer></v-spacer>
                <v-row>
                    <v-col cols="12">
                        <v-data-table 
                            :headers="headers2" 
                            :items="items2" 
                            :items-per-page="5" 
                            class="elevation-1">
                        </v-data-table>
                    </v-col>
                </v-row>

                <v-row>
                  <v-col cols="15">
                    <v-btn 
                          color="success" 
                          height="40" 
                          width="200"   
                          to="/patient">จัดเก็บข้อมูลผู้ป่วยโรคติดต่อ
                    </v-btn>
                  </v-col>

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
        </v-card>
    </v-container>
</template>

<script>
import http from "../sprint2/http-common";

export default {
  name: "Patientshow",
  data() {
    return {
      headers: [
        { text: "ID",value: "id"},
        { text: "Name",value: "patientfullname"},
        { text: "Person ID",value: "personId"},
        { text: "Gender", value: "gender.gender" },
        { text: "Birth Date", value: "patientbirthdate" },
        { text: "Blood Type", value: "bloodtype.bloodtype" },
        { text: "Phone", value: "phone" },
        { text: "Address", value: "address" },
        { text: "Patient Date", value: "patientdate" }
      ],
      headers2: [
        { text: "Person ID",value: "patient.personId"},
        { text: "Disease", value: "disease.disease" }
      ],
      items: [],
      items2: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getPatients() {
      http
        .get("/patient")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getDiseasePatient() {
      http
        .get("/diseasepatient")
        .then(response => {
          this.items2 = response.data;
          console.log(this.items2);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getPatients();
    this.getDiseasePatient();
  }
};
</script>