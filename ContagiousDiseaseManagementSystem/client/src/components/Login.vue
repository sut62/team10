<template>
  <v-app>
    <div class="text-center">
      <v-layout justify-center>
        <div v-if="whilelogin">
          <v-card class="mx-auto my-12" style="width: 600px;">
            <v-layout text-center wrap column>
              <v-toolbar-title class="headline text-uppercase">
                <v-toolbar        
                dense
                dark
                prominent           
                src="https://estaticos.muyinteresante.es/media/cache/760x570_thumb/uploads/images/article/5a252d3a5cafe8ab559dbc06/vih_0.jpg">   
                <v-row justify="center">
                  <v-col>          
                    <v-toolbar-title><h1>ระบบจัดการโรคติดต่อ</h1></v-toolbar-title>                
                  </v-col>
                </v-row>
                </v-toolbar>
                <br />
              </v-toolbar-title>
            </v-layout>
            
            <v-row>
              <v-row>
                <v-col>
                  <v-text-field
                    class="mx-auto"
                    style="width: 200px;"
                    label="อีเมล์"
                    name="login"
                    type="text"
                    color="light-blue darken-4"
                    prepend-icon="mdi-account-circle"
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
                    color="light-blue darken-4"
                    prepend-icon="mdi-lock"
                    v-model="password"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-row>
            <v-row>
              <v-col >
                <v-btn 
                  type="submit" tile height="40" 
                  width="120" color="primary" 
                  to="/register">สมัครสมาชิก</v-btn>
              </v-col>
              <v-spacer></v-spacer>
              <v-col>
                <v-btn
                  height="40" width="120"
                  color="success"
                  @click.prevent="findMedicallStaffss"
                  tile
                  type="submit"
                >เข้าสู่ระบบ</v-btn>
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
      passwordCheck: "",
      staffFullname: ""
    };
  },
  /* eslint-disable no-console */
  methods: {
    Logout() {
      alert("Logout !!!");
      this.$router.push("/");
    },
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
            this.staffFullname = response.data.fullname;
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
        .get("/medicalStaff")
        .then(response => {
          this.medicalstaff = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },

  mounted() {
    this.getMedicalStaffss();
  }
};
</script>
