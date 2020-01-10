<template>
  <v-app>
    <div class="text-center">
      <v-layout justify-center>
        <div v-if="authenticated">
          <v-app-bar>
            <v-toolbar>
              <v-toolbar-title>ล็อกอิน</v-toolbar-title>
              <div class="flex-grow-1">
                <v-toolbar-items>
                  <v-tabs>
                    <v-tab @click="pushHome">หน้าแรก</v-tab>
                    <v-tab @click="pushRegister">สมัครสมาชิก</v-tab>
                    <v-tab @click="pushContagion">จัดเก็บข้อมูลโรคติดต่อ</v-tab>
                    <v-tab @click="pushDiagnose"
                      >จัดเก็บการวินิจฉัยโรคติดต่อ</v-tab
                    >
                    <v-tab @click="pushPatient"
                      >จัดเก็บข้อมูลผู้ป่วยโรคติดต่อ</v-tab
                    >
                    <v-tab @click="pushvaccineinformation"
                      >บันทึกข้อมูลวัคซีนป้องกัน/ยา</v-tab
                    >
                    <v-tab @click="pushRiskarea">จัดเก็บพื้นที่เสี่ยง</v-tab>
                  </v-tabs>
                </v-toolbar-items>
              </div>
              <v-toolbar-title v-if="authenticated != ''"
                >Email : {{ email }}
                <v-tab @click="Logout">Logout</v-tab>
              </v-toolbar-title>
            </v-toolbar>
          </v-app-bar>
        </div>

        <div v-if="whilelogin">
          <v-card class="mx-auto my-12" style="width: 500px;">
            <v-card-title>เข้าสู่ระบบ</v-card-title>
            <v-row>
              <v-row>
                <v-col>
                  <v-text-field
                    class="mx-auto"
                    style="width: 200px;"
                    label="อีเมล์"
                    name="login"
                    type="text"
                    v-model="email"
                  ></v-text-field>
                </v-col>
                <v-col>
                  <v-text-field
                    class="mx-auto"
                    style="width: 200px;"
                    label="รหัสผ่าน"
                    name="password"
                    type="password"
                    v-model="password"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-row>
            <v-row>
              <v-col>
                <v-btn type="submit" tile color="primary" to="/register"
                  >สมัครสมาชิก</v-btn
                >
              </v-col>
              <v-spacer></v-spacer>
              <v-col>
                <v-btn
                  color="#008B00"
                  @click.prevent="findMedicallStaffss"
                  tile
                  type="submit"
                  >เข้าสู่ระบบ
                </v-btn>
              </v-col>
            </v-row>
          </v-card>
        </div>
      </v-layout>
    </div>
  </v-app>
</template>

<script>
import http from "../http-common";

export default {
  data: () => {
    return {
      email: "",
      password: "",
      medicalstaff: [],
      valid: false,
      whilelogin: true,
      authenticated: false,
      emailCheck: "",
      passwordCheck: ""
    };
  },
  /* eslint-disable no-console */
  methods: {
    findMedicallStaffss() {
      http
        .get("/medicalstaff/" + this.email)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.passwordCheck = response.data.password;
          } else {
            alert("อีเมลล์ หรือ รหัสผ่านผิด");
          }

          if (this.password == this.passwordCheck) {
            this.authenticated = true;
            this.email = response.data.email;
            this.whilelogin = false;
            alert("เข้าสู่ระบบเสร็จสิ้น");
            this.$router.push("/");
          }
        })
        .catch(e => {
          console.log(e);
          alert("อีเมลล์ หรือ รหัสผ่านผิด");
        });
    },
    getMedicalStaffss() {
      http
        .get("/medicalstaff")
        .then(response => {
          this.medicalstaff = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    Logout() {
      alert("Logout !!!");
      this.authenticated = false;
      this.whilelogin = true;
      this.email = "";
      this.password = "";
      this.$router.push("/");
    },

    pushContagion() {
      this.$router.push("/contagion");
    },
    pushDiagnose() {
      this.$router.push("/diagnose");
    },
    pushHome() {
      this.$router.push("/home");
    },
    pushPatient() {
      this.$router.push("/patient");
    },
    pushRegister() {
      this.$router.push("/register");
    },
    pushRiskarea() {
      this.$router.push("/riskarea");
    },
    pushvaccineinformation() {
      this.$router.push("/vaccineinformation");
    }
  },

  mounted() {
    this.getMedicalStaffss();
  }
};
</script>
