<template>
  <div class="home">
    <h1>Basketball Betting Aid</h1>
    <p>You must be authenticated to see this</p>
  <form @submit.prevent="retrieveStats">
  <select name="Player" id="playerDropdown" v-model="selectedPlayerId">
    <option v-for="player in playerList" :key="player.player_id" 
    :value="player.player_id" required
    >{{player.last_name}}, {{player.first_name}}</option>
  </select>
  
  <select name="Category" id="category" v-model="statRequest.category" @change="clear" required>
    <option value="" disabled selected>Select A Stat Category</option>
    <option value="assists" :selected="statRequest.category === 'assists'">Assists</option>
    <option value="blocks" :selected="statRequest.category === 'blocks'">Blocks</option>
    <option value="fieldGoals" :selected="statRequest.category === 'fieldGoals'">Field Goals</option>
    <option value="freeThrows" :selected="statRequest.category === 'freeThrows'">Free Throws</option>
    <option value="points" :selected="statRequest.category === 'points'">Points</option>
    <option value="rebounds" :selected="statRequest.category === 'rebounds'">Rebounds</option>
    <option value="steals" :selected="statRequest.category === 'steals'">Steals</option>
    <option value="threePointers" :selected="statRequest.category === 'threePointers'">Three Pointers</option>
  </select>
  
  <select name="Line" id="line" v-model="selectedLine">
    <option value="" disabled selected>Select A Line</option>
  </select>
  
  <button type="submit">Submit</button>
  </form>
  <table v-show="requestedStats != null && (statRequest.category === 'assists' || 
  statRequest.category === 'blocks' || statRequest.category === 'points' || 
  statRequest.category === 'rebounds' || statRequest.category === 'steals')">
    <tr>
      <th class="date-column">Date</th>
      <th class="value-column">Value</th>
    </tr>
    <tr v-for="(value, date) in requestedStats" :key="date" :class="rowClass(value, selectedLine)">
      <td class="date-column">{{ date }}</td>
      <td class="value-column">{{ value }}</td>
    </tr>
  </table>
  <table v-show="requestedStats != null && (statRequest.category === 'fieldGoals' 
  || statRequest.category === 'freeThrows' || statRequest.category === 'threePointers')">
    <tr>
      <th class="date-column">Date</th>
      <th class="made-column">Made</th>
      <th class="attempts-column">Attempts</th>
    </tr>
    <tr v-for="(data, date) in requestedStats" :key="date" :class="rowClass(data.shotsMade, selectedLine)">
      <td class="date-column">{{date}}</td>
      <td class="made-column">{{data.shotsMade}}</td>
      <td class="attempts-column">{{data.attempts}}</td>
    </tr>
  </table>
  </div>
</template>

<script>
import StatService from '../services/StatService.js';
import PlayerService from '../services/PlayerService.js';

export default {
  name: "home",
  data() {
    return {
      playerList: [],
      selectedPlayerId: Number(0),
      statRequest: {
        playerId: this.selectedPlayerId,
        category: "",
      },
      selectedLine: "",
      requestedStats: null,
    };
  },

    computed: {
    rowClass() {
      return (value, selectedLine) => {
        if (value > selectedLine) {
          return 'higher-than-line';
        } else if (value < selectedLine) {
          return 'lower-than-line';
        } else {
          return '';
        }
      };
    },
    },
  methods: {

    getPlayerPoints(id) {
      return StatService.getPlayerPoints(id);
    },

    getPlayerListFromDatabase() {
      PlayerService.getPlayersFromDatabase()
      .then((response) => {
        this.playerList = response.data.map((player) => {
          return {
            player_id: player.player_id,
            first_name: player.first_name,
            last_name: player.last_name,
          }
        })
        if (this.playerList.length > 0) {
            this.selectedPlayerId = this.playerList[0].player_id;
        }
      })
      .catch((error) => {
        console.log(error)
      })
    },

    clear() {
      this.requestedStats = null;
    },
    retrieveStats() {
      this.requestedStats = null;
      if (this.statRequest.category === 'points') {
        StatService.getPlayerPoints(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'assists') {
        StatService.getPlayerAssists(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'blocks') {
        StatService.getPlayerBlocks(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'rebounds') {
        StatService.getPlayerRebounds(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'steals') {
        StatService.getPlayerSteals(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'fieldGoals') {
        StatService.getPlayerFieldGoals(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'freeThrows') {
        StatService.getPlayerFreeThrows(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'threePointers') {
        StatService.getPlayerThrees(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        });
      }

    },
  },

  created() {
    this.getPlayerListFromDatabase();
  },
  
  mounted() {
    const lineDropdown = document.getElementById('line');
    function generateOptions(start, end, step) {
      for (let i = start; i <= end; i += step) {
        const option = document.createElement("option");
        option.value = i;
        option.textContent = i.toString();
        lineDropdown.appendChild(option);
      }
    }

    generateOptions(0.5, 50.5, 1);
  },

};
</script>
<style scoped>
select {
  margin: 10px;
  font-family: Calibri, Geneva, Tahoma, sans-serif;
  background-color: lightgrey;
  font-size: 15px;
}
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  text-align: center;
  font-family: Calibri, Geneva, Tahoma, sans-serif;
  background-color: black;
  min-height: 100vh;
  margin: 0;
  padding: 0;
}

h1 {
  font-size: 24px;
  margin-bottom: 10px;
  color: #d6ad60;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

button:hover {
  background-color: #ffcd58;
}

button {
  background-color: #ff9636;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin: 10px;
  font-family: Calibri, Geneva, Tahoma, sans-serif;
  font-size: 15px;
}

th {
  font-weight: bold;
}

table {
  display: table;
  width: 1fr;
  color: white;
}

.date-column {
  text-align: center;
}

.value-column {
  text-align: center;
}

.made-column {
  text-align: center;
}

.attempts-column {
  text-align: center;
}

.higher-than-line {
  background-color: #81c784;
}

.lower-than-line {
  background-color: #e57373;
}

</style>
