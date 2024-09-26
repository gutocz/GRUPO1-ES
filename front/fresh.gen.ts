// DO NOT EDIT. This file is generated by Fresh.
// This file SHOULD be checked into source version control.
// This file is automatically updated during development when running `dev.ts`.

import * as $_404 from "./routes/_404.tsx";
import * as $_app from "./routes/_app.tsx";
import * as $cadastro from "./routes/cadastro.tsx";
import * as $esqueciSenha from "./routes/esqueciSenha.tsx";
import * as $index from "./routes/index.tsx";
import * as $logado_aluno_aluno_ from "./routes/logado/aluno/[aluno].tsx";
import * as $logado_aluno_perfil from "./routes/logado/aluno/perfil.tsx";
import * as $logado_aluno_recarga from "./routes/logado/aluno/recarga.tsx";
import * as $logado_funcionario_funcionario_ from "./routes/logado/funcionario/[funcionario].tsx";
import * as $logado_funcionario_criarCardapio from "./routes/logado/funcionario/criarCardapio.tsx";
import * as $logado_funcionario_editar from "./routes/logado/funcionario/editar.tsx";
import * as $logado_funcionario_perfil from "./routes/logado/funcionario/perfil.tsx";
import * as $loginAluno from "./routes/loginAluno.tsx";
import * as $loginFuncionario from "./routes/loginFuncionario.tsx";
import * as $novaSenha from "./routes/novaSenha.tsx";
import * as $AlunoLogado from "./islands/AlunoLogado.tsx";
import * as $CriarCardapio from "./islands/CriarCardapio.tsx";
import * as $FuncionarioLogado from "./islands/FuncionarioLogado.tsx";
import * as $LoginAluno from "./islands/LoginAluno.tsx";
import * as $Modal from "./islands/Modal.tsx";
import * as $ModalAddPrato from "./islands/ModalAddPrato.tsx";
import * as $ModalCardapio from "./islands/ModalCardapio.tsx";
import * as $ModalEditPrato from "./islands/ModalEditPrato.tsx";
import * as $ModalPedirMarmita from "./islands/ModalPedirMarmita.tsx";
import * as $Navbar from "./islands/Navbar.tsx";
import * as $Token from "./islands/Token.tsx";
import * as $cadastro_1 from "./islands/cadastro.tsx";
import { type Manifest } from "$fresh/server.ts";

const manifest = {
  routes: {
    "./routes/_404.tsx": $_404,
    "./routes/_app.tsx": $_app,
    "./routes/cadastro.tsx": $cadastro,
    "./routes/esqueciSenha.tsx": $esqueciSenha,
    "./routes/index.tsx": $index,
    "./routes/logado/aluno/[aluno].tsx": $logado_aluno_aluno_,
    "./routes/logado/aluno/perfil.tsx": $logado_aluno_perfil,
    "./routes/logado/aluno/recarga.tsx": $logado_aluno_recarga,
    "./routes/logado/funcionario/[funcionario].tsx":
      $logado_funcionario_funcionario_,
    "./routes/logado/funcionario/criarCardapio.tsx":
      $logado_funcionario_criarCardapio,
    "./routes/logado/funcionario/editar.tsx": $logado_funcionario_editar,
    "./routes/logado/funcionario/perfil.tsx": $logado_funcionario_perfil,
    "./routes/loginAluno.tsx": $loginAluno,
    "./routes/loginFuncionario.tsx": $loginFuncionario,
    "./routes/novaSenha.tsx": $novaSenha,
  },
  islands: {
    "./islands/AlunoLogado.tsx": $AlunoLogado,
    "./islands/CriarCardapio.tsx": $CriarCardapio,
    "./islands/FuncionarioLogado.tsx": $FuncionarioLogado,
    "./islands/LoginAluno.tsx": $LoginAluno,
    "./islands/Modal.tsx": $Modal,
    "./islands/ModalAddPrato.tsx": $ModalAddPrato,
    "./islands/ModalCardapio.tsx": $ModalCardapio,
    "./islands/ModalEditPrato.tsx": $ModalEditPrato,
    "./islands/ModalPedirMarmita.tsx": $ModalPedirMarmita,
    "./islands/Navbar.tsx": $Navbar,
    "./islands/Token.tsx": $Token,
    "./islands/cadastro.tsx": $cadastro_1,
  },
  baseUrl: import.meta.url,
} satisfies Manifest;

export default manifest;
