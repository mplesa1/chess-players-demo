<template>
    <div v-if="playerAoe">
        <b-modal @ok="submitPlayer(player)" v-model="playerAoe" id="modal-center" centered title="Dodaj igrača" v-bind:ok-title="modalButton">

            <form id="saveForm">
                <div class="form-group">

                    <label for="playerFirstName" class="col-form-label">Ime igrača:</label>
                    <input v-model="player.firstName" type="text" class="form-control" id="playerFirstName" required>
                </div>
                <div class="form-group">
                    <label for="playerLastName" class="col-form-label">Prezime igrača:</label>
                    <input v-model="player.lastName" type="text" class="form-control" id="playerLastName" required>
                </div>
                <div class="form-group">
                    <label for="playerScore" class="col-form-label">Score:</label>
                    <input v-model="player.score" type="number" class="form-control" id="playerScore" required>
                </div>
            </form>
        </b-modal>
    </div>
</template>

<script>
	var app = new Vue({
		el: '#root',
		data: {
			players: [],
			playerAoe: false,
			modalButton: "",
			player: {
				id: '',
				firstName: "",
				lastName: "",
				score: null
			}
		},
		methods: {
			getPlayer(playerId) {
				axios.get("/players/" + playerId).then(function (response) {

					this.player = response.data;

					console.error(this.match);

				}.bind(this));

			},
			submitPlayer(player) {
				if (player) {
					if (player.id) {
						this.updatePlayer(player);
					} else {
						this.savePlayer(player);
						player = null;
					}
				} else {
					return console.log('nema playera');
				}
			},
			savePlayer(player) {
				axios.post("/players", {
					firstName: player.firstName,
					lastName: player.lastName,
					score: player.score,

				}).then((response) => {

					console.log("add uspio");
					this.getPlayers();

				}).catch(error => {

					console.warn(error);
				});
			},
			updatePlayer(player) {
				axios.put("/players/" + player.id, {
					id: player.id,
					firstName: player.firstName,
					lastName: player.lastName,
					score: player.score,

				}).then((response) => {

					console.warn("update uspio");
					this.getPlayers();

				}).catch(error => {

					console.log(error);
				});
			},
			reset(player) {
				for (let field in player) {
					player[field] = ''
				}
				return player
			}
		}

	});
</script>