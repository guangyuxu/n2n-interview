<script setup lang="ts">
defineProps<{
  msg: string
}>()
</script>

<template>
  <div class="greetings">
    <h1 class="green">{{ msg }}</h1>
    <h2 color="red">Please make sure the backend service has been started, and ignore the home and about page</h2><br>
    <h3>
      Gavin X created the page for interview. Please click the below Alien Clock to find out my features
      <p>There are three features</p>
      <ul>
        <li>1. Universal Clock, which presents both Alien time and Earth Time(UTC), it refresh two times per second</li>
        <li>2. Earth Time(UTC) to Alien Time, input the hour:minute:second year-month-day of the Earth and click the Convert button </li>
        <li>3. Alien to Earth Time(UTC) Time, input the hour:minute:second year-month-day of the Alien and click the Convert button </li>
      </ul>
      <p>Note: The Convert button is only available when input data is legal</p>
    </h3>
  </div>
</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

.greetings h1,
.greetings h3 {
  text-align: center;
}

@media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
}
</style>
