<template>
  <v-container>
    <!-- <v-app-bar style = "background: white;" app>
      <v-toolbar-title  class="headline text-uppercase" >
        <span class="font-weight">SportStaduim</span>   
      </v-toolbar-title>
      <v-row justify="center" style="margin-right:190px">
      <v-btn-toggle group>
      <v-btn @click="pushReserve" color="#00E676" style="color:#000000">จองสนาม</v-btn>
      <v-btn @click="pushBorrow" color="#76FF03" style="color:#000000">ยืมอุปกรณ์</v-btn>
      <v-btn @click="pushCustomer" color="#00E676" style="color:#000000">สมัครสมาชิก</v-btn>
      <v-btn @click="pushEmployee" color="#76FF03" style="color:#000000">ข้อมูลพนักงาน</v-btn>
      <v-btn @click="pushPayment" color="#00E676" style="color:#000000">ชำระเงินค่าสมาชิก</v-btn>
      <v-btn @click="pushSportEquipment" color="#76FF03" style="color:#000000">ข้อมูลอุปกรณ์</v-btn>
      </v-btn-toggle>
      
      </v-row>
  </v-app-bar>
   -->
    <v-card style="width:80%; margin:auto; background-color:#FFFFFF">
      <v-container>
        <v-layout text-center wrap>
          <v-flex mb-4>
            <br />
            <h1 class="display-1 font-weight-bold mb-3">
              ข้อมูลวัคซีนป้องกัน/ยา
            </h1>
          </v-flex>
        </v-layout>
        <v-row justify="center">
          <v-col cols="5">
            <v-text-field
              v-model="search"
              label="ค้นหา"
              prepend-icon="mdi mdi-file-find"
              outlined
              hide-details
            ></v-text-field>
          </v-col>
          <v-col cols="11">
            <v-data-table
              :headers="headers"
              :items="items"
              :items-per-page="5"
              class="elevation-1"
              :search="search"
            >
            </v-data-table>
          </v-col>

          <v-col cols="3">
            <v-text-field
              outlined
              label="ต้องการลบ ID ข้อมูลที่: "
              prepend-icon="mdi mdi-delete-forever"
              v-model="vaccineinformation.vaccineinformationid"
            ></v-text-field>
            <p v-if="vaccineCheck != ''">
              ID ข้อมูลที่จะทำการลบ : {{ listId }}
              <v-btn
                class=""
                @click="deleteVaccineInformation"
                color="#D50000"
                style="color:#FFFFFF"
                >ลบ</v-btn
              >
            </p>
          </v-col>

          <v-col cols="2">
            <div class="">
              <v-btn
                @click="findVaccineInformation"
                depressed
                large
                color="#000000"
                style="color:#FFFFFF;"
                >ยืนยัน</v-btn
              >
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "viewvaccineinformation",
  data() {
    return {
      search: "",
      headers: [
        {
          text: "ID ข้อมูล",
          align: "left",
          sortable: false,
          value: "vaccineinformationid"
        },

        { text: "ขื่อวัคซีนป้องกัน/ยา", value: "vaccineid.vaccinename" },
        { text: "ประเภทวัคซีนป้องกัน/ยา",value: "typevaccineid.typevaccinelist"        },
        { text: "วันที่จัดเก็บ", value: "storagedate" },
        { text: "วันที่หมดอายุ", value: "expiredate" },
        { text: "ทำรายการโดย", value: "fullname.fullname" }
      ],
      items: [],
      vaccineinformation: {
        vaccineinformationid: null
      },
      valid: false,
      vaccineCheck: false,
      vaccineinformationid: null
    };
  },
  methods: {
    /* eslint-disable no-console */
    getVaccineinformation() {
      http
        .get("/vaccineinformation")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    findVaccineInformation() {
      http
        .get(
          "/vaccineinformation/" + this.vaccineinformation.vaccineinformationid
        )
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.vaccineinformationid = response.data.vaccineinformationid;
            this.vaccineCheck = response.status;
          } else {
            this.clear();
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    deleteVaccineInformation() {
      http
        .delete(
          "/vaccineinformation/" + this.vaccineinformation.vaccineinformationid
        )
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          alert("ทำการลบรายการข้อมูลเรียบร้อย");
          location.reload();
        })
        .catch(e => {
          console.log(e);
        });
    },
    pushvaccineinformation() {
      this.$router.push("/vaccineinformation");
    },
    refreshList() {
      this.getVaccineinformation();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getVaccineinformation();
  }
};
</script>
