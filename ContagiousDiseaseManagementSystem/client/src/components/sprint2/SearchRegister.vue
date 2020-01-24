<template>
  <v-container>
    <v-card class="mx-auto text-center" style="width: 950px;">
      <!-- หัวเรื่อง -->
      <v-layout text-center wrap>
        <v-flex mb-4>
          <br />
          <h1 class="display-2 font-weight-bold mb-3">ค้นหาตำแหน่งทางการแพทย์</h1>
        </v-flex>
      </v-layout>

      <!-- ค้นหาด้วย Position -->
      <v-row>
        <v-col>
          <label class="headline">กรุณาเลือกตำแหน่งทางการแพทย์</label>
          <v-select
            class="mx-auto"
            style="width: 500px"
            label="เลือกตำแหน่งทางการแพทย์"
            outlined
            v-model="medicalStaff.position"
            :items="position"
            item-text="position"
            item-value="id"
            :rules="[v => !!v || 'Item is required']"
            required
          ></v-select>
          <!-- search Button -->
          <v-btn 
            color="primary" 
            height="50"
            width="100" 
            @click="findbyPosition" dark>ค้นหา
            <v-icon dark right>mdi-magnify</v-icon>
          </v-btn>
        </v-col>
      </v-row>

      <!-- ตาราง -->
      <div v-if="medicalstaffcheck">
        <v-data-table
          :headers="headers"
          :items="medicalstaffdata"
          :items-per-page="5"
          class="elevation-1 mx-auto text-center"
          style="width: 850px;"
        ></v-data-table>

        <!-- ปุ่มกลับ -->
        <v-row>
          <v-col>
            <v-btn 
              class="ma-5"
              color="grey lighten-2" 
              height="50" 
              width="80"   
              to="/home">
              <v-icon dark left>mdi-arrow-left</v-icon>
              กลับ
            </v-btn>
          </v-col>
        </v-row>
      </div>

    <div v-if="saveUnsuccessful">
        <v-alert outlined dense text type="error" prominent border="left">
            <strong>ไม่พบข้อมูล</strong>
        </v-alert>
    </div>

      <div v-if="saveSuccessful">
        <v-alert dense outlined text prominent type="success">ค้นหาสำเร็จ</v-alert>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import http from "../sprint2/http-common";
export default {
  data() {
    return {
      saveUnsuccessful: false,
      saveSuccessful: false,
      position: [],
      medicalStaff: [],
      medicalstaffdata: [{
        fullname: '',
        gender: '',
        date: '',
        position: '',
        phone: '',
        email: '',
        address: '',
        province: ''
      }],
      medicalstaffcheck: false,
      headers: [
        { text: 'ชื่อสกุล', value: 'fullname'},
        { text: 'เพศ', value: 'gender.gender'},
        { text: 'วันเดือนปีเกิด', vlue: 'date'},
        { text: 'ตำแหน่งทางการแพทย์', value: 'position.position'},
        { text: 'เบอร์โทรศัพท์', value: 'phone'},
        { text: 'อีเมล์', value: 'email'},
        { text: 'ที่อยู่', value: 'address'},
        { text: 'จังหวัด', value: 'province.province'},
      ]
    };
  },
  /* eslint-disable no-console */
  methods: {

    getMedicalStaff() {
      http
        .get("/medicalStaff")
        .then(response => {
          this.medicalStaff = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

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

    findbyPosition() {
      http
        .get("/medicalStaffposition/" + this.medicalStaff.position)
        .then(response => {
          this.medicalstaffdata = response.data;
          console.log(response);
          this.saveSuccessful = true;
          this.saveUnsuccessful = false;
          if (response.data != null) {
            this.medicalstaffcheck = true;
          }
        })
        .catch(e => {
          console.log(e);
          this.saveSuccessful = false;
          this.saveUnsuccessful = true;
        });
      this.submitted = true;
    }
  },
  mounted() {
    this.getMedicalStaff();
    this.getPosition();
  }
};
</script>
