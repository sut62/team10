<template>
  <v-container>
    <v-app-bar style="background: white;" app>
      <v-toolbar-title class="headline text-uppercase">
        <span class="font-weight">ContagiousDiseaseManagementSystem</span>
      </v-toolbar-title>
    </v-app-bar>

    <v-card style="width:80%; margin:auto; background-color:#FFFFFF" >
      <v-container>
        <v-layout text-center wrap>
          <v-flex mb-4>
            <br />
            <span class="mdi mdi-calendar mdi-48px"></span>
            <h1 class="display-1 font-weight-bold mb-3">แบบฟอร์มบันทึกข้อมูลวัคซีนป้องกัน/ยา</h1>
          </v-flex>
        </v-layout>

        <v-row justify="center">
          <v-col cols="8">
            <v-form v-model="valid" ref="form">
              <v-row justify-center>
                <v-col cols="25">
                  <v-select
                    v-model="vaccineinformation.fullname"
                    :items="medicalStaffs"
                    item-text="fullname"
                    item-value="id"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- medicalStaffname --"
                    required
                  ></v-select>
                </v-col>
              </v-row>

              <v-row justify-center>
                <v-col cols="25">
                  <v-select
                    v-model="vaccineinformation.vaccineid"
                    :items="vaccines"
                    item-text="vaccinename"
                    item-value="vaccineid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- vaccine --"
                    required
                  ></v-select>
                </v-col>
              </v-row>

              <v-row justify-center>
                <v-col cols="25">
                  <v-select
                    label="-- typeVaccine --"
                    v-model="vaccineinformation.typevaccineid"
                    :items="typevaccines"
                    item-text="typevaccinelist"
                    item-value="typevaccineid"
                    :rules="[v => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-col>
              </v-row>

              <v-row justify-center>
                <v-col cols="15" sm="12">
                  <v-menu
                    v-model="menu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    min-width="120px"
                  >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        style="width:80%"
                        prepend-icon="mdi mdi-calendar"
                        v-model="strdate"
                        label="วันที่บันทึกข้อมูลวัคซีนป้องกัน/ยา"
                        clearable
                        readonly
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="strdate" @input="menu = false"></v-date-picker>
                  </v-menu>
                </v-col>
                <v-col cols="15" sm="12">
                  <v-menu
                    v-model="menu1"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    min-width="120px"
                  >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        style="width:80%"
                        prepend-icon="mdi mdi-calendar"
                        v-model="expdate"
                        label="วันที่หมดอายุของวัคซีนป้องกัน/ยา"
                        clearable
                        readonly
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="expdate" @input="menu1 = false"></v-date-picker>
                  </v-menu>
                </v-col>
              </v-row>

              <v-row justify-right>
                <v-col cols="3">
                  <!-- <v-btn-toggle group > -->
                  <v-btn
                    @click="saveVaccineinformation"
                    style="color:#FFFFFF"
                    :class="{ red: !valid, green: valid }"
                  >บันทึก</v-btn>
                </v-col>
                <v-spacer></v-spacer>
                <v-col cols="3">
                  <v-btn @click="clear" color="#D50000" style="color:#FFFFFF">ยกเลิก</v-btn>
                </v-col>
                <v-spacer></v-spacer>
                <v-col cols="3">
                  <v-btn
                    @click="viewvaccineinformation"
                    color="#000000"
                    style="color:#FFFFFF"
                  >ดูบันทึก</v-btn>
                  <!-- </v-btn-toggle> -->
                </v-col>
                <v-spacer></v-spacer>
                <v-col cols=15>
                    <v-btn 
                      color="success" 
                      height="40" 
                      width="100"   
                      to="/">กลับ</v-btn>
                    </v-col>
              </v-row>
              <br />
            </v-form>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "vaccineinformation",
  data() {
    return {
      vaccineinformation: {
        fullname: "",
        vaccineid: null,
        typevaccineid: null
      },
      strdate: new Date().toISOString().substr(0, 10),
      expdate: new Date().toISOString().substr(0, 10),
      menu: false,
      menu1: false,
      medicalStaffs: [],
      vaccines: null,
      typevaccines: null,
      valid: false
    };
  },

  methods: {
    /* eslint-disable no-console */
    //  ดึงข้อมูล fullname ใส่ combobox
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
    // ดึงข้อมูล vaccine ใส่ combobox
    getVaccines() {
      http
        .get("/vaccine")
        .then(response => {
          this.vaccines = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล typevaccine ใส่ combobox
    getTypeVaccines() {
      http
        .get("/typevaccine")
        .then(response => {
          this.typevaccines = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveVaccineinformation() {
      http
        .post(
          "/vaccineinformation/" +
            this.vaccineinformation.fullname +
            "/" +
            this.vaccineinformation.vaccineid +
            "/" +
            this.vaccineinformation.typevaccineid +
            "/" +
            this.strdate +
            "/" +
            this.expdate,
          this.vaccineinformation
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ");
          this.$router.push("/viewvaccineinformation");
        })
        .catch(e => {
          console.log(e);
          alert("บันทึกไม่สำเร็จ");
        });
      this.submitted = true;
    },
    viewvaccineinformation() {
      this.$router.push("/viewvaccineinformation");
    },
    clear() {
      this.$refs.form.reset();

      this.vaccineCheck = false;
    },

    pushvaccineinformation() {
      this.$router.push("/vaccineinformation");
    },

    refreshList() {
      this.getMedicalStaffs();
      this.getVaccines();
      this.getTypeVaccines();
    }
  },
  mounted() {
    this.getMedicalStaffs();
    this.getVaccines();
    this.getTypeVaccines();
  }
};
</script>
