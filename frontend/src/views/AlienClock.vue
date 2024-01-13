<template>
  <div>
    <table>
      <tr class="title">
        <th colspan=2>Welcome to Alien Plane</th>
      </tr>
      <tr class="title">
        <th colspan=2>Universal Clock</th>
      </tr>
      <tr class="subtitle">
        <th style="width: 360px;">Alien Time</th><th style="width: 360px;">Earth Time(UTC)</th>
      </tr>
      <tr class="content">
        <td>
          {{ formatNumber(alienClock.hour, 2) }}:{{ formatNumber(alienClock.minute, 2) }}:{{ formatNumber(alienClock.second, 2) }}
        </td><td>
          {{ formatNumber(earthClock.hour, 2) }}:{{ formatNumber(earthClock.minute, 2) }}:{{ formatNumber(earthClock.second, 2) }}
        </td>
      </tr>
      <tr class="content">
        <td>
          {{ formatNumber(alienClock.year, 4) }}-{{ formatNumber(alienClock.month, 2) }}-{{ formatNumber(alienClock.day, 2) }}
        </td><td>
          {{ formatNumber(earthClock.year, 4) }}-{{ formatNumber(earthClock.month, 2) }}-{{ formatNumber(earthClock.day, 2) }}
        </td>
      </tr>
      <tr class="title">
        <th colspan=2>Earth Time(UTC) to Alien Time    >>> <button @click="earthTimeToAlienTime" :disabled="earthToAlienDisabled">Convert</button></th>
      </tr>
      <tr class="subtitle">
        <th>Alien Time</th><th>Earth Time(UTC)</th>
      </tr>
      <tr class="content">
        <td>
          {{ formatNumber(earthToAlien.alienClock.hour, 2) }}:{{ formatNumber(earthToAlien.alienClock.minute, 2) }}:{{ formatNumber(earthToAlien.alienClock.second, 2) }}
        </td>
        <td>
          <input type="number" placeholder="00" max="23" min="0" class="input-time" v-model="earthToAlien.earthClock.hour" @input="validInput($event, 'earthToAlien')">:
          <input type="number" placeholder="00" max="59" min="0" class="input-time" v-model="earthToAlien.earthClock.minute" @input="validInput($event, 'earthToAlien')">:
          <input type="number" placeholder="00" max="59" min="0" class="input-time" v-model="earthToAlien.earthClock.second" @input="validInput($event, 'earthToAlien')">
        </td>
      </tr>
      <tr class="content">
        <td>
          {{ formatNumber(earthToAlien.alienClock.year, 4) }}-{{ formatNumber(earthToAlien.alienClock.month, 2) }}-{{ formatNumber(earthToAlien.alienClock.day, 2) }}
        </td>
        <td>
          <input type="number" placeholder="0000" max="9999" min="1970" v-model="earthToAlien.earthClock.year" @input="validInput($event, 'earthToAlien')">-
          <input type="number" placeholder="00" max="12" min="1" v-model="earthToAlien.earthClock.month" @input="validInput($event, 'earthToAlien')">-
          <input type="number" placeholder="00" max="31" min="1" v-model="earthToAlien.earthClock.day" @input="validInput($event, 'earthToAlien')">
        </td>

      </tr>
      <tr class="title">
        <th colspan=2>Alien Time to Earth Time(UTC)    >>> <button @click="alienTimeToEarthTime" :disabled="alienToEarthDisabled">Convert</button></th>
      </tr>
      <tr class="subtitle">
        <th>Alien Time</th><th>Earth Time(UTC)</th>
      </tr>
      <tr class="content">
        <td>
          <input type="number" placeholder="00" max="35" min="0" class="input-time" v-model="alienToEarth.alienClock.hour" @input="validInput($event, 'alienToEarth')">:
          <input type="number" placeholder="00" max="89" min="0" class="input-time" v-model="alienToEarth.alienClock.minute" @input="validInput($event, 'alienToEarth')">:
          <input type="number" placeholder="00" max="89" min="0" class="input-time" v-model="alienToEarth.alienClock.second" @input="validInput($event, 'alienToEarth')">
        </td>
        <td>
          {{ formatNumber(alienToEarth.earthClock.hour, 2) }}:{{ formatNumber(alienToEarth.earthClock.minute, 2) }}:{{ formatNumber(alienToEarth.earthClock.second, 2) }}
        </td>
      </tr>
      <tr class="content">
        <td>
          <input type="number" placeholder="0000" max="9999" min="2804" v-model="alienToEarth.alienClock.year" @input="validInput($event, 'alienToEarth')">-
          <input type="number" placeholder="00" max="18" min="1" v-model="alienToEarth.alienClock.month" @input="validInput($event, 'alienToEarth')">-
          <input type="number" placeholder="00" max="48" min="1" v-model="alienToEarth.alienClock.day" @input="validInput($event, 'alienToEarth')">
        </td>
        <td>
          {{ formatNumber(alienToEarth.earthClock.year, 4) }}-{{ formatNumber(alienToEarth.earthClock.month, 2) }}-{{ formatNumber(alienToEarth.earthClock.day, 2) }}
        </td>
      </tr>
    </table>
  </div>
</template>


<script lang="ts">
import axios from 'axios';
const ALIEN_MONTHLY_DAYS = [0, 44, 42, 48, 40, 48, 44, 40, 44, 42, 40, 40, 42, 44, 48, 42, 40, 44, 38];
const EARTH_MONTHLY_DAYS = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
export default {
  
  data() {
    return {
      intervalId: 500,
      earthToAlienDisabled: true,
      alienToEarthDisabled: true,
      alienClock: {
        year: 0,
        month: 0,
        day: 0,
        hour: 0,
        minute: 0,
        second: 0,
      },
      earthClock: {
        year: 0,
        month: 0,
        day: 0,
        hour: 0,
        minute: 0,
        second: 0,
      },
      earthToAlien: {
        alienClock: {
          year: 0,
          month: 0,
          day: 0,
          hour: 0,
          minute: 0,
          second: 0,
        },
        earthClock: {
          year: 0,
          month: 0,
          day: 0,
          hour: 0,
          minute: 0,
          second: 0,
        },
      },
      alienToEarth: {
        alienClock: {
          year: 0,
          month: 0,
          day: 0,
          hour: 0,
          minute: 0,
          second: 0,
        },
        earthClock: {
          year: 0,
          month: 0,
          day: 0,
          hour: 0,
          minute: 0,
          second: 0,
        },
      }

    };
  },
  mounted() {
    this.intervalId = setInterval(this.fetchData, 500);
  },
  unmounted() {
    clearInterval(this.intervalId);
  },
  methods: {
    validInput(event, groupName: string){
      if (parseInt(event.target.min) <= parseInt(event.target.value) && parseInt(event.target.value) <= parseInt(event.target.max)){
        let submitable = true;
        if (groupName == 'alienToEarth') {
          const c = this.alienToEarth.alienClock;
          submitable = submitable && 2804 <= c.year && c.year <= 9999;
          submitable = submitable && 1 <= c.month && c.month <= 18;
          submitable = submitable && 1 <= c.day && c.day <= ALIEN_MONTHLY_DAYS[c.month];
          submitable = submitable && 0 <= c.hour && c.hour < 36;
          submitable = submitable && 0 <= c.minute && c.minute < 90;
          submitable = submitable && 0 <= c.second && c.second < 90;
          this.alienToEarthDisabled = !submitable
        }
        if (groupName == 'earthToAlien') {
          const c = this.earthToAlien.earthClock;
          submitable = submitable && 1970 <= c.year && c.year <= 9999;
          submitable = submitable && 1 <= c.month && c.month <= 12;
          submitable = submitable && 1 <= c.day && c.day <= EARTH_MONTHLY_DAYS[c.month];
          submitable = submitable && 0 <= c.hour && c.hour < 24;
          submitable = submitable && 0 <= c.minute && c.minute < 60;
          submitable = submitable && 0 <= c.second && c.second < 60;
          this.earthToAlienDisabled = !submitable
        }
      }else {
        if (parseInt(event.target.min) > parseInt(event.target.value)) {
          event.target.value = event.target.min
        } else if (parseInt(event.target.value) > parseInt(event.target.max)) {
          event.target.value = event.target.max
        }
        if (groupName == 'alienToEarth') {
          this.alienToEarthDisabled = true;
        }
        if (groupName == 'earthToAlien') {
          this.earthToAlienDisabled = true;
        }
      }
    },
    fetchData() {
      // Example: Make a GET request to an API endpoint
      axios.get('/alien-clock/now')
        .then(response => {
          // Handle the successful response
          this.alienClock = response.data;
        })
        .catch(error => {
          // Handle errors
          console.error('Error fetching data:', error);
        });
        axios.get('/earth-clock/now')
        .then(response => {
          // Handle the successful response
          this.earthClock = response.data;
        })
        .catch(error => {
          // Handle errors
          console.error('Error fetching data:', error);
        });
    },
    alienTimeToEarthTime() {
      axios.post('/alien-clock/alien-to-earth', this.alienToEarth.alienClock)
      .then(response => {
          // Handle the successful response
          this.alienToEarth.earthClock = response.data;
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      })
    },
    earthTimeToAlienTime() {
      axios.post('/alien-clock/earth-to-alien', this.earthToAlien.earthClock)
      .then(response => {
          // Handle the successful response
          this.earthToAlien.alienClock = response.data;
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      })
    },
    formatNumber(v: number, size: number) {
      let s = v.toString();
      const zeros = Math.max(0, size - s.length);
      return '0'.repeat(zeros) + s;
    },
  },
};
</script>

<style>
@media (min-width: 1024px) {
  .now {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
  .time-component {
    min-width: 400px; 
    max-width: 400px; 
    text-align: center;
  }

  .title {
    height: 120px;
    vertical-align: bottom;
    font-size: 32px;
    color: hsla(160, 100%, 37%, 1);
  }
  .subtitle {
    font-size: 26px;
    color: rgb(169, 197, 43, 1);
  }
  .content {
    color: rgb(255, 255, 255, 1);
    font-weight: bold;
  }
  input {
    width: 80px;
    background-color: rgba(255,255,255,0.7);
  }
  .input-time {
    font-size: larger !important;
    width: 55px;
  }
  button {
    font-size: 28px;
    color: hsla(160, 100%, 37%, 1);
    border: 2px;
    border-color: red;
  }
  td {
    text-align: center;
  }
}
</style>
