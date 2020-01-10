<template>
    <v-container>
        <v-card class="mx-auto" style="max-width: 600px" >
          
            <v-layout  text-center wrap>
                <v-flex mb-4>
                    <br />
                    <h1 class="font-weight-black mb-3">สมัครสมาชิก</h1>
                </v-flex>
            </v-layout>

            <v-row>
                <v-col cols="15">
                    <v-text-field 
                        class="mx-auto"
                        style="width: 500px"
                        label="ชื่อสกุล" 
                        v-model="fullname"
                    ></v-text-field>
                </v-col>
            </v-row>

             <v-row justify="center">
                <v-col cols="6">
                    <v-menu
                    ref="menu1"
                    v-model="menu1"
                    :close-on-content-click="false"
                    transition="scale-transition"
                    offset-y
                    full-width
                    max-width="290px"
                    min-width="290px"
                    >
                    <template v-slot:activator="{ on }">
                    <v-text-field
                        class="mx-auto"
                        style="width: 188px"
                        v-model="dateFormatted"
                        label="วันเดือนปีเกิด"
                        @blur="date = parseDate(dateFormatted)"
                        v-on="on"
                    ></v-text-field>
                    </template>
                        <v-date-picker v-model="date" no-title @input="menu1 = false"></v-date-picker>
                    </v-menu>
                </v-col>

                <v-col cols="6">
                    <v-text-field 
                        class="mx-auto"
                        style="width: 188px"
                        label="เบอร์โทรศัพท์" 
                        v-model="phone"
                    ></v-text-field>
                </v-col>
            </v-row>
            
            <v-row align="center">
                <v-col cols="6">
                    <v-select
                        class="mx-auto"
                        style="width: 200px"
                        label="เพศ"
                        outlined
                        v-model="medicalstaff.gender"
                        :items="gender"
                        item-text="gender"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                    ></v-select>
                </v-col>

                <v-col cols="6">
                    <v-select
                        class="mx-auto"
                        style="width: 200px"
                        label="ตำแหน่ง"
                        outlined
                        v-model="medicalstaff.position"
                        :items="position"
                        item-text="position"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                    ></v-select>
                </v-col>

                <v-col cols="6">
                    <v-select
                        class="mx-auto"
                        style="width: 200px"
                        label="จังหวัด"
                        outlined
                        v-model="medicalstaff.province"
                        :items="province"
                        item-text="province"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                    ></v-select>
                </v-col>
            </v-row>

             <v-row>
                <v-col cols="15">
                    <v-text-field 
                        class="mx-auto"
                        style="width: 500px"
                        label="Email" 
                        v-model="email"
                    ></v-text-field>
                </v-col>

                <v-col cols="15">
                    <v-text-field 
                        class="mx-auto"
                        style="width: 500px"
                        label="รหัสผ่าน" 
                        v-model="password"
                        type="password"
                    ></v-text-field>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="15">
                    <v-textarea 
                        class="mx-auto"
                        style="width: 500px"
                        label="ที่อยู่" 
                        v-model="address"
                    ></v-textarea>
                </v-col>
            </v-row>
         
        
            <v-row > 
              <v-col cols=15>
                    <v-btn 
                      color="success" 
                      height="40" 
                      width="100"   
                      to="/">กลับ</v-btn>
                    </v-col>
               
               <v-spacer></v-spacer>

                <v-col cols=15>
                    <v-btn 
                        color="success" 
                        height="40" 
                        width="100" 
                        @click="saveMedicalStaff" dark>บันทึก
                    </v-btn>
                </v-col>
            </v-row>
        </v-card>
    </v-container>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {
      province: [],
      gender: [],
      position: [],
      medicalStaff: {
        gender: "",
        position: "",
        province: ""
      },
      address: "",
      phone: "",
      fullname: "",
      email: "",
      password: "",
      date: new Date().toISOString().substr(0, 10),
      dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
      menu1: false,

      medicalstaff: [
        {
          province: "",
          gender: "",
          position: ""
        }
      ]
    };
  
  },
  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    }
  },
  watch: {
    date() {
      this.dateFormatted = this.formatDate(this.date);
    }
  },
  
  /* eslint-disable no-console */
  methods: {
    // ดึงข้อมูลชื่อจังหวัดใส่ combobox
    getProvince() {
      http
        .get("/province")
        .then(response => {
          this.province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        
        });
    },
    // ดึงข้อมูลเพศใส่ combobox
    getGender() {
      http
        .get("/gender")
        .then(response => {
          this.gender = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูลตำแหน่งใส่ combobox
    getPosition() {
      http
        .get("/position")
        .then(response => {
          this.position = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function เมื่อกดปุ่ม บันทึก
    saveMedicalStaff() {
      console.log(this.date)
      http
        .post(
          "/medicalstaff/" +
            this.medicalstaff.gender +
            "/" +
            this.medicalstaff.position +
            "/" +
            this.medicalstaff.province +
            "/" +
            this.address +
            "/" +
            this.fullname +
            "/" +
            this.email +
            "/" +
            this.phone +
            "/" +
            this.password +
            "/" +
            this.date,
          this.medicalstaff
        )
        .then(response => {
          console.log(response);
          alert("บันทึกข้อมูลสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("ไม่สามารถสมัครได้");
        });
    },
    formatDate(date) {
      if (!date) return null;
      const [year, month, day] = date.split("-");
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;
      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    }
  },
  mounted() {
    this.getProvince();
    this.getGender();
    this.getPosition();
  }
};
</script>