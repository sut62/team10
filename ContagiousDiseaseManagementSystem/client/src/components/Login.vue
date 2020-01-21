<template>
  <v-app>
    <div class="text-center">
      <v-layout justify-center>
        <div v-if="whilelogin">
          <v-card class="mx-auto my-12" style="width: 500px;">
            <v-card-title>Login</v-card-title>
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
                  color="#4CAF50"
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
            this.$router.push("/home");
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
  },

  mounted() {
    this.getMedicalStaffss();
  }
};
</script>
