classDiagram
direction BT
class Associar_equipa {
  - String data_creacao
  - int id_equipa
  - DateTimeFormatter dtf
  - int id_competicao
  + equals(Object) boolean
  + getId_competicao() int
  + setId_competicao(int) void
  + toString() String
  + getData_creacao() String
  + getId_equipa() int
  + setData_creacao(String) void
  + setId_equipa(int) void
}
class Associar_juri {
  - int id_competicao
  - int id_juri
  - DateTimeFormatter dtf
  - String data_creacao
  + getId_competicao() int
  + equals(Object) boolean
  + getData_creacao() String
  + toString() String
  + setId_juri(int) void
  + setId_competicao(int) void
  + setData_creacao(String) void
  + getId_juri() int
}
class Associar_robo {
  - int id
  - String robot
  - int pontos
  - String tempo
  - String velocidade
  + setId(int) void
  + hashCode() int
  + getTempo() String
  + setVelocidade(String) void
  + toString() String
  + getPontos() int
  + getId() int
  + getRobot() String
  + equals(Object) boolean
  + setPontos(int) void
  + getVelocidade() String
  + setRobot(String) void
  + setTempo(String) void
}
class Authentication {
  - String password
  - String username
  + getPassword() String
  + getUsername() String
}
class Competicao {
  - int nrondas
  - int max_equipas
  - String estado
  - DateTimeFormatter dtf
  - int id
  - String desc
  - String data_creacao
  - int id_organizador
  - ArrayList~String~ equipasRegistadas
  - boolean jurilogadoAssociado
  - String nome
  - String data_conclusao
  - ArrayList~String~ jurisRegistados
  - String nome_organizador
  + getEquipasRegistadas() ArrayList~String~
  + getNrondas() int
  + getData_conclusao() String
  + toString() String
  + getId_organizador() int
  + setJurilogadoAssociado(boolean) void
  + equals(Object) boolean
  + isJurilogadoAssociado() boolean
  + setDesc(String) void
  + getNome_organizador() String
  + setNrondas(int) void
  + setNome(String) void
  + setNome_organizador(String) void
  + setMax_equipas(int) void
  + setData_conclusao(String) void
  + getEstado() String
  + setId_organizador(int) void
  + getDesc() String
  + setEquipasRegistadas(ArrayList~String~) void
  + getNome() String
  + setData_creacao(String) void
  + setEstado(String) void
  + getId() int
  + setId(int) void
  + getMax_equipas() int
  + getData_creacao() String
  + setJurisRegistados(ArrayList~String~) void
  + getJurisRegistados() ArrayList~String~
}
class DBConnection {
  ~ String DBNAME
  ~ String USER
  ~ String PASS
  ~ String URL
  + getConnection() Connection
}
class DeleteController {
  + deleteJuri(int, int) int
}
class DeleteHandler {
  - LoginHandler lh
  + deleteJuri(RoutingContext) void
}
class Elementos_equipa {
  - int id_equipa
  - String nome
  - String email
  - String data_creacao
  - DateTimeFormatter dtf
  + toString() String
  + getId_equipa() int
  + equals(Object) boolean
  + setId_equipa(int) void
  + setData_creacao(String) void
  + getData_creacao() String
  + setEmail(String) void
  + getEmail() String
  + getNome() String
  + setNome(String) void
}
class Equipa {
  - String password
  + getPassword() String
  + toString() String
  + setPassword(String) void
  + equals(Object) boolean
  + hashCode() int
}
class GetController {
  - Robot robot
  - Associar_robo associarrobo
  + getNomeRobot(int) Robot
  + getCompeticoes() ArrayList~Competicao~
  + getCompeticao(int) Competicao
  + getRondasCompeticoes(int) ArrayList~Ronda~
  + getRobot(int) Robot
  + getDadosRonda(int) ArrayList~Associar_robo~
}
class GetHandler {
  + getRondasCompeticao(RoutingContext) void
  + getCompeticoes(RoutingContext) void
  + getDadosRonda(RoutingContext) void
  + getNomeRobot(RoutingContext) void
  + getCompeticao(RoutingContext) void
}
class Juri {
  - String password
  - String email
  + getPassword() String
  + setPassword(String) void
  + toString() String
  + getEmail() String
  + setEmail(String) void
  + equals(Object) boolean
}
class LoginController {
  - Organizador organizador
  - Juri juri
  - Equipa equipa
  + verfLoginOrganizador(String, String) boolean
  + setOrganizador(Organizador) void
  + verfLoginJuri(String, String) boolean
  + setJuri(Juri) void
  + getJuri() Juri
  + verfLoginEquipa(String, String) boolean
  + verfLogin(String, String) int
  + getOrganizador() Organizador
  + getEquipa() Equipa
  + setEquipa(Equipa) void
}
class LoginHandler {
  - User user
  - Authentication authProvider
  ~ boolean isAuthenticated
  + verflogin(RoutingContext) void
  + getAuthProvider() Authentication
  + getUser() User
  + isAuthenticated() boolean
  + setAuthProvider(Authentication) void
  + setAuthenticated(boolean) void
  + setUser(User) void
}
class MQTTCli {
  - String USER_NAME
  - int BROKER_PORT
  - String topico1
  - String BROKER_HOST
  - Vertx vertx
  - String PASSWORD
  - MqttClient client
  + subscrever(String) void
  - connectHandler(MqttClient) Handler~AsyncResult~MqttConnAckMessage~~
  - topicHandler() Handler~MqttPublishMessage~
}
class MqttController {
  + newMqttCli(Vertx) MQTTCli
  + inserirBD(String) int
}
class Organizador {
  - String password
  - String email
  + email(String) Organizador
  + toString() String
  + getEmail() String
  + setPassword(String) void
  + setEmail(String) void
  + getPassword() String
  + equals(Object) boolean
  + password(String) Organizador
}
class RegistoController {
  - Elementos_equipa elementosEquipa
  - Organizador organizador
  - Equipa equipa
  - Juri juri
  - Robot robot
  - Competicao competicao
  + criarRondas(int) int
  + novoOrganizador() void
  + registarCompeticao(int, String, String, int, int) int
  + novoRobot() void
  + registarRobot(int, String, String) int
  + novaEquipa() void
  + novaCompeticao() void
  + registarElemento(int, String, String) int
  + novoElemento() void
  + novoJuri() void
  + registarOrganizador(String, String, String) int
  + inscreverJuri(int, int) int
  + registarJuri(String, String, String) int
  + registarEquipa(String, String) int
}
class RegistoHandler {
  - LoginHandler lh
  + addOrganizador(RoutingContext) void
  + addElemento(RoutingContext) void
  + addRobot(RoutingContext) void
  + addCompeticao(RoutingContext) void
  + addEquipa(RoutingContext) void
  + inscreverJuri(RoutingContext) void
  + getLh() LoginHandler
  + addJuri(RoutingContext) void
}
class Robot {
  - String data_creacao
  - String nome
  - String mac_adr
  - DateTimeFormatter dtf
  - int id
  - int id_equipa
  + getData_creacao() String
  + setData_creacao(String) void
  + getId_equipa() int
  + getNome() String
  + getId() int
  + setMac_adr(String) void
  + setId(int) void
  + toString() String
  + equals(Object) boolean
  + setNome(String) void
  + setId_equipa(int) void
  + getMac_adr() String
}
class Ronda {
  - int id
  - DateTimeFormatter dtf
  - String data_creacao
  - String numero
  - String tipo
  - int id_competicao
  + setId_competicao(int) void
  + setNumero(String) void
  + setId(int) void
  + setTipo(String) void
  + getId_competicao() int
  + equals(Object) boolean
  + getData_creacao() String
  + getNumero() String
  + toString() String
  + setData_creacao(String) void
  + getId() int
  + getTipo() String
}
class Servidor {
  + main(String[]) void
}
class TelegramBot {
  - Vertx vertx
  - String chat_id
  + getBotUsername() String
  + sendMessage(String) void
  + onUpdateReceived(Update) void
  + getBotToken() String
}
class TelegramBotController {
  - TelegramBot bot
  + sendMessage(String) void
  + setBot(TelegramBot) void
  + newTelegramBot(Vertx) TelegramBot
  + getBot() TelegramBot
  + telegramBot(TelegramBot) void
}
class UpdateController {
  + novoTempo(int, int, int, int, int) int
  + novaPontuacao(int, String) int
}
class UpdateHandler {
  + updateTempo(RoutingContext) void
  + updatePontos(RoutingContext) void
  + updateRobot(RoutingContext) void
}
class User {
  - String nome
  - int id
  - DateTimeFormatter dtf
  - String data_creacao
  + getId() int
  + setData_creacao(String) void
  + getData_creacao() String
  + toString() String
  + getNome() String
  + setNome(String) void
  + setId(int) void
  + equals(Object) boolean
}
class VerticleRSJson {
  - UpdateHandler updateHandler
  - DeleteHandler deleteHandler
  - RegistoHandler registoHandler
  - GetHandler getHandler
  - MQTTCli mqttCli
  - TelegramBot bot
  - LoginHandler loginHandler
  - int porta
  + initTelegramBot() void
  + stop() void
  + initHandlers() void
  + start(Promise~Void~) void
  + initMqttClient() void
}

DeleteController  ..>  DBConnection 
DeleteHandler  ..>  DeleteController : «create»
DeleteHandler "1" *--> "lh 1" LoginHandler 
DeleteHandler  ..>  LoginHandler : «create»
DeleteHandler  ..>  User 
Equipa  -->  User 
GetController "1" *--> "associarrobo 1" Associar_robo 
GetController  ..>  Associar_robo : «create»
GetController  ..>  Competicao : «create»
GetController  ..>  DBConnection 
GetController  ..>  LoginHandler : «create»
GetController  ..>  Robot : «create»
GetController "1" *--> "robot 1" Robot 
GetController  ..>  Ronda : «create»
GetController  ..>  User 
GetHandler  ..>  GetController : «create»
Juri  -->  User 
LoginController  ..>  DBConnection 
LoginController "1" *--> "equipa 1" Equipa 
LoginController  ..>  Equipa : «create»
LoginController  ..>  Juri : «create»
LoginController "1" *--> "juri 1" Juri 
LoginController  ..>  Organizador : «create»
LoginController "1" *--> "organizador 1" Organizador 
LoginController  ..>  User 
LoginHandler  ..>  Authentication : «create»
LoginHandler "1" *--> "authProvider 1" Authentication 
LoginHandler  ..>  LoginController : «create»
LoginHandler "1" *--> "user 1" User 
MQTTCli  ..>  MqttController : «create»
MqttController  ..>  DBConnection 
MqttController  ..>  MQTTCli : «create»
MqttController  ..>  TelegramBotController : «create»
Organizador  -->  User 
RegistoController "1" *--> "competicao 1" Competicao 
RegistoController  ..>  Competicao : «create»
RegistoController  ..>  DBConnection 
RegistoController "1" *--> "elementosEquipa 1" Elementos_equipa 
RegistoController  ..>  Elementos_equipa : «create»
RegistoController  ..>  Equipa : «create»
RegistoController "1" *--> "equipa 1" Equipa 
RegistoController "1" *--> "juri 1" Juri 
RegistoController  ..>  Juri : «create»
RegistoController "1" *--> "organizador 1" Organizador 
RegistoController  ..>  Organizador : «create»
RegistoController  ..>  Robot : «create»
RegistoController "1" *--> "robot 1" Robot 
RegistoController  ..>  Ronda : «create»
RegistoController  ..>  User 
RegistoHandler  ..>  LoginHandler : «create»
RegistoHandler "1" *--> "lh 1" LoginHandler 
RegistoHandler  ..>  RegistoController : «create»
RegistoHandler  ..>  User 
Servidor  ..>  VerticleRSJson : «create»
TelegramBotController  ..>  TelegramBot : «create»
TelegramBotController "1" *--> "bot 1" TelegramBot 
UpdateController  ..>  DBConnection 
UpdateHandler  ..>  GetController : «create»
UpdateHandler  ..>  Robot 
UpdateHandler  ..>  UpdateController : «create»
VerticleRSJson  ..>  DeleteHandler : «create»
VerticleRSJson "1" *--> "deleteHandler 1" DeleteHandler 
VerticleRSJson "1" *--> "getHandler 1" GetHandler 
VerticleRSJson  ..>  GetHandler : «create»
VerticleRSJson  ..>  LoginHandler : «create»
VerticleRSJson "1" *--> "loginHandler 1" LoginHandler 
VerticleRSJson "1" *--> "mqttCli 1" MQTTCli 
VerticleRSJson  ..>  MqttController : «create»
VerticleRSJson  ..>  RegistoHandler : «create»
VerticleRSJson "1" *--> "registoHandler 1" RegistoHandler 
VerticleRSJson "1" *--> "bot 1" TelegramBot 
VerticleRSJson  ..>  TelegramBotController : «create»
VerticleRSJson "1" *--> "updateHandler 1" UpdateHandler 
VerticleRSJson  ..>  UpdateHandler : «create»
