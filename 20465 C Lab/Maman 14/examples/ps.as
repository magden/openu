;ps.as
	.entry STRADD
	.entry MAIN
	.extern REVERSE
	.extern PRTSTR
	.extern COUNT
STRADD: .data 1,2,3,4
STR:	.string "abcdef"
LASTCHAR: .data 0
LEN: .data 0
;comment
MAIN:	lea	STR,STRADD
		jsr COUNT
		jsr PRTSTR
		mov *STRADD,LASTCHAR
		add LEN,LASTCHAR
		dec LASTCHAR
		jsr REVERSE
		jsr	PRTSTR
		hlt
