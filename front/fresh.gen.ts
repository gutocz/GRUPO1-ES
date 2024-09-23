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
import * as $logado_funcionario_editar from "./routes/logado/funcionario/editar.tsx";
import * as $logado_funcionario_perfil from "./routes/logado/funcionario/perfil.tsx";
import * as $loginAluno from "./routes/loginAluno.tsx";
import * as $loginFuncionario from "./routes/loginFuncionario.tsx";
import * as $novaSenha from "./routes/novaSenha.tsx";
import * as $AlunoLogado from "./islands/AlunoLogado.tsx";
import * as $FuncionarioLogado from "./islands/FuncionarioLogado.tsx";
import * as $Modal from "./islands/Modal.tsx";
import * as $ModalAddPrato from "./islands/ModalAddPrato.tsx";
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
    "./routes/logado/funcionario/editar.tsx": $logado_funcionario_editar,
    "./routes/logado/funcionario/perfil.tsx": $logado_funcionario_perfil,
    "./routes/loginAluno.tsx": $loginAluno,
    "./routes/loginFuncionario.tsx": $loginFuncionario,
    "./routes/novaSenha.tsx": $novaSenha,
  },
  islands: {
    "./islands/AlunoLogado.tsx": $AlunoLogado,
    "./islands/FuncionarioLogado.tsx": $FuncionarioLogado,
    "./islands/Modal.tsx": $Modal,
    "./islands/ModalAddPrato.tsx": $ModalAddPrato,
    "./islands/cadastro.tsx": $cadastro_1,
  },
  baseUrl: import.meta.url,
} satisfies Manifest;

export default manifest;