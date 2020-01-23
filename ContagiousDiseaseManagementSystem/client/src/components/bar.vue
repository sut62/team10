<template>
  <v-toolbar>
    <div id="bar" class="flex-grow-1">
      <v-toolbar-items>
        <v-tabs>
          <v-tab @click="pushHome">หน้าแรก</v-tab>
          <v-tab @click="pushRegister">สมัครสมาชิก</v-tab>
          <v-tab @click="pushContagion">จัดเก็บข้อมูลโรคติดต่อ</v-tab>
          <v-tab @click="pushDiagnose">จัดเก็บการวินิจฉัยโรคติดต่อ</v-tab>
          <v-tab @click="pushPatient">จัดเก็บข้อมูลผู้ป่วยโรคติดต่อ</v-tab>
          <v-tab @click="pushvaccineinformation">บันทึกข้อมูลวัคซีนป้องกัน/ยา</v-tab>
          <v-tab @click="pushRiskarea">จัดเก็บพื้นที่เสี่ยง</v-tab>
          <v-tab @click="pushSearchriskarea">ค้นหาพื้นที่เสี่ยง</v-tab>
        </v-tabs>
      </v-toolbar-items>
    </div>
    <v-toolbar-title>
      <v-tab @click="Logout">Logout</v-tab>
    </v-toolbar-title>
  </v-toolbar>
</template>

<script>
export default {
    methods: {
    
    Logout() {
      alert("Logout !!!");
      this.authenticated = false;
      this.whilelogin = true;
      this.email = [];
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
    pushSearchriskarea() {
      this.$router.push("/searchriskarea");
    },
    pushvaccineinformation() {
      this.$router.push("/vaccineinformation");
    }
  }
}
</script>