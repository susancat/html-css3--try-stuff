CREATE SCHEMA TopTrump;

CREATE TABLE Game
(GamesNo             INT             NOT NULL,
HumanWin             INT             NOT NULL,
AIWin                INT             NOT NULL,
NoDraws              INT             NOT NUll,
GameLength           INT             NOT NULL,
Primary Key(GamesNo, HumanWin, AIWin, NoDraws, GameLength));

INSERT INTO Game(GamesNo, HumanWin, AIWin, NoDraws, GameLength)
VALUES (1, 2, 7, 4, 100);

SELECT *
FROM Game;

SELECT SUM(GamesNo) As Total_games
FROM   Game;

SELECT SUM(HumanWin) As Human_score 
FROM   Game;

SELECT SUM(AIWin) As AI_score
FROM   Game;

SELECT AVG(NoDraws) As Ave_Draws
FROM   Game;

SELECT  MAX(GameLength) As Length_Game
FROM   Game;

/*================================================================*/
/*================================================================*/
/* =======Alternate Design ====================*/
CREATE SCHEMA TopTrump;

CREATE TABLE Game
(GameName            VARCHAR(15)     NOT NULL,
GamesNo              INT             NOT NULL,
HumanWin             INT             NOT NULL,
AIWin                INT             NOT NULL,
NoDraws              INT             NOT NUll,
LongestGame          INT             NOT NULL,
Primary Key(GameName));

/*=====================================*/
INSERT INTO Game(GameName, GamesNo, HumanWin, AIWin, NoDraws, GameLength)
VALUES ('Varname' , Numberofgames , NoofHumanWin, NoofAIwin, NoofDraws, GameLength)



/*=====================================*/
SELECT *
FROM Game;