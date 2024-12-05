CREATE TABLE board (board_id INT AUTO_INCREMENT PRIMARY KEY);
create table board_board_cells (board_board_id integer not null, board_cells_id integer not null unique);
CREATE TABLE cell (hotel_owner_player_id INTEGER UNIQUE,hotel_rent INTEGER NOT NULL,hotel_worth INTEGER NOT NULL,id INTEGER AUTO_INCREMENT,jail_penalty INTEGER NOT NULL,sequence_on_board  INTEGER NOT NULL, treasure_value INTEGER NOT NULL,cell_type ENUM('EMPTY', 'HOTEL', 'JAIL', 'TREASURE') NOT NULL, PRIMARY KEY (id));
CREATE TABLE dice (id INT AUTO_INCREMENT,last_used_output_index INT NOT NULL,PRIMARY KEY (id));
create table dice_outputs (dice_id integer not null, output integer);
CREATE TABLE game (board_board_id INT UNIQUE,dice_id INT UNIQUE,game_id INT AUTO_INCREMENT,next_player_player_id INT UNIQUE,number_of_turns_completed INT NOT NULL,status ENUM('COMPLETED', 'CREATED', 'INPROGRESS'),PRIMARY KEY (game_id));
create table game_players (game_game_id integer not null, players_player_id integer not null unique);
CREATE TABLE player (current_position_on_board INT NOT NULL,player_id INT AUTO_INCREMENT,player_position INT NOT NULL,total_balance INT NOT NULL,name VARCHAR(255),PRIMARY KEY (player_id));


ALTER TABLE board_board_cells ADD CONSTRAINT FK5al6eddedscmmdoqh9vxjfav8 FOREIGN KEY (board_cells_id) REFERENCES cell(id);
ALTER TABLE board_board_cells ADD CONSTRAINT FKhul7w5t98tinc155b5bw1x4j8 FOREIGN KEY (board_board_id) REFERENCES board(id);
ALTER TABLE cell ADD CONSTRAINT FK762594yoomjplvuisyerckcqw FOREIGN KEY (hotel_owner_player_id) REFERENCES player(player_id);
ALTER TABLE dice_outputs ADD CONSTRAINT FKcjkhkbrpfyropxl9rk9l0rxsp FOREIGN KEY (dice_id) REFERENCES dice(dice_id);
ALTER TABLE game ADD CONSTRAINT FKana02t2dv69dnew6d6gb2xx6a FOREIGN KEY (board_board_id) REFERENCES board(board_id);
ALTER TABLE game ADD CONSTRAINT FK7hjdfdnbwm8ma272d6f4ih8q2 FOREIGN KEY (dice_id) REFERENCES dice(id);
ALTER TABLE game ADD CONSTRAINT FKlldqstvhmqsgcnhe7bpro838a FOREIGN KEY (next_player_player_id) REFERENCES player(player_id);
ALTER TABLE game_players ADD CONSTRAINT FKey7nfyw2nv5o3dptg1ksi93vl FOREIGN KEY (players_player_id) REFERENCES player(player_id);
ALTER TABLE game_players ADD CONSTRAINT FKtaqwqj699sigdheaesl1idxk2 FOREIGN KEY (game_game_id) REFERENCES game(game_id);

