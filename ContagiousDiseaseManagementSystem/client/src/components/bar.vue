<template>
  <v-card class="mx-auto" tile>
    <v-tabs vertical>
      <v-col>
      <v-tab @click="pushHome"><h3>หน้าแรก</h3></v-tab>
      <v-tab @click="pushviewvaccineinformation" ><h3>ตารางข้อมูลวัคซีนป้องกัน/ยา</h3></v-tab>
      <v-tab @click="pushviewDataVaccine"><h3>แสดงข้อมูลวัคซีนป้องกัน/ยา</h3></v-tab>
      <v-tab @click="pushvaccineinformation"><h3>บันทึกข้อมูลวัคซีนป้องกัน/ยา</h3></v-tab>
      <v-tab @click="pushSearchRegister"><h3>ค้นหาข้อมูลของสมาชิก</h3></v-tab>
      <v-tab @click="pushContagion"><h3>เก็บข้อมูลโรคติดต่อ</h3></v-tab>
      <v-tab @click="pushStatistics"><h3>เก็บสถิติการเกิดโรคติดต่อ</h3></v-tab>
      <v-tab @click="pushPatient"><h3>จัดเก็บข้อมูลผู้ป่วยโรคติดต่อ</h3></v-tab>
      <v-tab @click="pushPatientshow"><h3>แสดงข้อมูลผู้ป่วยโรคติดต่อ</h3></v-tab>
      <v-tab @click="pushDiagnose"><h3>จัดเก็บการวินิจฉัยโรคติดต่อ</h3></v-tab>
      <v-tab @click="pushDiagnoseView"><h3>แสดงผลการวินิจฉัยโรคติดต่อ</h3></v-tab>
      <v-tab @click="pushRiskarea"><h3>จัดเก็บพื้นที่เสี่ยง</h3></v-tab>
      <v-tab @click="pushSearchriskarea"><h3>ค้นหาพื้นที่เสี่ยง</h3></v-tab>
      </v-col>
    </v-tabs>
  </v-card>
</template>

<script>
export default {
  methods: {
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
    pushSearchriskarea() {
      this.$router.push("/searchriskarea");
    },
    pushvaccineinformation() {
      this.$router.push("/vaccineinformation");
    },
    pushviewvaccineinformation() {
      this.$router.push("/viewvaccineinformation");
    },
    pushviewDataVaccine() {
      this.$router.push("/viewDatavaccine");
    },
    pushPatientshow() {
      this.$router.push("/patientshow");
    },
    pushDiagnoseView() {
      this.$router.push("/diagnoseView");
    },
    pushSearchRegister() {
      this.$router.push("/searchregister");
    },
    pushStatistics() {
      this.$router.push("/statistics");
    }
  }
};
</script>