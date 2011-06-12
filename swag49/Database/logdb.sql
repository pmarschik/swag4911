drop table log_log4j;
CREATE TABLE log_log4j (
	LogDate TIMESTAMP,
	Logger VARCHAR (4000),
	Priority VARCHAR(4000),
	Loc_ClassName VARCHAR(4000),
	Loc_MethodName VARCHAR(4000),
	Loc_FileName VARCHAR(4000),
	Loc_LineNumber VARCHAR(4000),
	Msg VARCHAR(4000),
	Throwable VARCHAR(4000));
