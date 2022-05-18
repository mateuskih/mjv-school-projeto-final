<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center pt-3">
      </div>
    </div>
    <div class="row">
      <div class="col-12 justify-content-center d-flex pt-3">
        <div id="signup" class="flex-item border">
          <h2 class="pt-4 pl-4">Criar Conta</h2>
          <form @submit="signup" class="pt-4 pl-4 pr-4">
            <div class="form-group">
              <label for="Nome">Nome</label>
              <input
                type="nome"
                v-model="nome"
                class="form-control"
                required
              />
            </div>

            <div class="form-group">
              <label for="Email">Email</label>
              <input
                type="email"
                v-model="email"
                class="form-control"
                required
              />
            </div>

            <div class="form-row">
              <div class="col">
                <div class="form-group">
                  <label>Cpf</label>
                  <input
                    type="text"
                    v-model="cpf"
                    class="form-control"
                    required
                  />
                </div>
              </div>
              <div class="col">
                <div class="form-group">
                  <label>Rg</label>
                  <input
                    type="text"
                    v-model="rg"
                    class="form-control"
                    required
                  />
                </div>
              </div>
            </div>

             <div class="form-group">
              <label for="Senha">Whatsapp</label>
              <input
                type="tel"
                v-model="whatsapp"
                class="form-control"
                required
              />
            </div>

            <div class="form-group">
              <label for="Senha"> Senha</label>
              <input
                type="password"
                v-model="senha"
                class="form-control"
                required
              />
            </div>

            <div class="form-group">
              <label for="Password"> Confirmar senha</label>
              <input
                type="password"
                v-model="confirmSenha"
                class="form-control"
                required
              />
            </div>

          <label>Endereço</label><br>
            <div class="form-row">
              <div class="col">
                <div class="form-group">
                  <label>Logradouro</label>
                  <input
                    type="text"
                    v-model="logradouro"
                    class="form-control"
                    required
                  />
                </div>
              </div>
              <div class="col">
                <div class="form-group">
                  <label>Numero</label>
                  <input
                    type="text"
                    v-model="numero"
                    class="form-control"
                    required
                  />
                </div>
              </div>
              <div class="col">
                <div class="form-group">
                  <label>Bairro</label>
                  <input
                    type="text"
                    v-model="bairro"
                    class="form-control"
                    required
                  />
                </div>
              </div>
            </div>
            <button class="btn btn-primary mt-2">Criar Conta</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import swal from "sweetalert";
export default {
  props: ["baseURL"],
  data() {
    return {
      nome: null,
      email: null,
      cpf: null,
      rg: null,
      whatsapp: null,
      senha: null,
      confirmSenha: null,
      logradouro: null,
      numero: null,
      bairro: null,
    };
  },
  methods: {
    async signup(e) {
      e.preventDefault();
      if (this.senha === this.confirmSenha) {
        const cliente = {
          nome: this.nome,
          email: this.email,
          cpf: this.cpf,
          rg: this.rg,
          whatsapp: this.whatsapp,
          senha: this.senha,
          confirmSenha: this.confirmSenha,
          endereco: {
            logradouro: this.logradouro,
            numero: this.numero,
            bairro: this.bairro
          },
        };
        console.log("user", cliente);
        await axios
          .post(`${this.baseURL}clientes`, cliente)
          .then(() => {
            this.$router.replace("/");
            swal({
              text: "Cliente criado com sucesso, efetue o login!",
              icon: "success",
            });
          })
          .catch((err) => console.log("err", err));
      } else {
        swal({
          text: "Senhas diferentes",
          icon: "error",
        });
      }
    },
  },
};
</script>
<style scoped>
.btn-primary {
  background-color: #f0c14b;
  color: #000;
}

@media screen and (min-width: 992 px) {
  #signup {
    width: 40%;
  }
}
</style>
