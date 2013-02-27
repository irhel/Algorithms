/*
 * Algorithm:
 * Keep a 2 two-dimensional arrays which are going to keep the attacked positions by black and white figures.
 * Then, determine the position of the white and black king and determine whether that field is under attack.
 * @
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#define REP(i,n) for(i=1;i<n;i++)
#define SIZE 8 /*Size of the board */
int i,j,n,m,k,t1,t2;
int whiteX,whiteY;
int blackX,blackY;
char **balloc(void); /*Allocate board*/
void print_board(char **ptr);
int check_empty(char **board);/*Returns 1 if the board is empty( all '.' character) otherwise returns 0 */
void calculate(char **board, char **attacked_by_white, char **attacked_by_black);
int main(void)
{
	int game=0;
	char c;
	char **attacked_by_white,**attacked_by_black,**board;
	int i,j;
	attacked_by_white = balloc();
	attacked_by_black = balloc();
	board = balloc();
	
	while(1)
	{
	   for(i = 0; i < SIZE; i++)
	{
		for(j = 0; j < SIZE; j++)
		{
			scanf("%c",&board[i][j]);
		}
		getchar();
	}
	getchar();
	
	if(check_empty(board)==1){
	    return 0;
	    }
	calculate(board,attacked_by_white,attacked_by_black);
	
	if(attacked_by_white[blackX][blackY] == '*')
	{
	       game++;
	       board = balloc();
		attacked_by_white = balloc();
		attacked_by_black = balloc();
		printf("Game #%d: black king is in check.\n",game);
		continue;
	}
       if(attacked_by_black[whiteX][whiteY] == '*'){
              game++;
              board = balloc();
		attacked_by_white = balloc();
		attacked_by_black = balloc();
		printf("Game #%d: white king is in check.\n",game);
		continue;
		}

	else{
	
		game++;
		board = balloc();
		attacked_by_white = balloc();
		attacked_by_black = balloc();
		printf("Game #%d: no king is in check.\n",game);
		continue;
	}
	
	}
	return 0;
}
char **balloc(void)
{
	int i,j;
	char **p = (char **)malloc(sizeof(char *)*SIZE);
	assert(p!=NULL);
	for(i = 0; i < SIZE; i++)
	{
		p[i] = (char *) malloc(sizeof(char)*SIZE);
		assert(p[i]!=NULL);
	}
	for(i = 0; i < SIZE; i++)
	{
		for(j = 0; j < SIZE; j++)
		{
			p[i][j] = '.';
		}
	}
	return p;
}
void print_board(char **ptr)
{
	int i,j;
	for(i = 0; i < SIZE; i++)
	{
		for(j = 0; j < SIZE; j++)
		{
			printf("%c",ptr[i][j]);
		}
		putchar('\n');
	}
}
int check_empty(char **board)
{
	int i,j;
	for(i = 0; i < SIZE; i++)
	{
		for(j = 0; j < SIZE; j++)
		{
			if(board[i][j] != '.')
				return 0;
		}
	}
	return 1;
}
void calculate(char **board, char **attacked_by_white, char **attacked_by_black)
{
    for(i = 0; i < SIZE; i++)
	{
		for(j = 0; j < SIZE; j++)
		{

			if(board[i][j] == 'P')
			{
				if((i-1) >= 0)
			    {
					if((j+1)<SIZE)
						attacked_by_white[i-1][j+1] = '*';
				    if((j-1) >= 0)
						attacked_by_white[i-1][j-1] = '*';
				}
				
			}
			if(board[i][j] == 'p')
			{
				if((i+1) < SIZE)
				{
					if((j+1)<SIZE)
						attacked_by_black[i+1][j+1] = '*';
					if((j-1)>=0)
						attacked_by_black[i+1][j-1] = '*';
				}
				
			}
			if(board[i][j] == 'N')
			{
				if((i-2)>=0)
				{
					if(j + 1 < SIZE)
						attacked_by_white[i-2][j+1] = '*';
					if(j - 1 >= 0)
						attacked_by_white[i-2][j-1] = '*';
				}
				if(i - 1 >= 0 )
				{
					if(j + 2 < SIZE)
						attacked_by_white[i-1][j+2] = '*';
					if( j - 2 >= 0)
						attacked_by_white[i-1][j-2] = '*';
				}
				if( i + 2 < SIZE)
				{
					if( j + 1 < SIZE)
						attacked_by_white[i+2][j+1]  = '*';
					if(j - 1 >= 0)
						attacked_by_white[i+2][j-1] = '*';
				}
				if( i + 1 < SIZE)
				{
					if(j - 2 >= 0)
						attacked_by_white[i+1][j-2] = '*';
					if(j + 2 < SIZE)
						attacked_by_white[i+1][j+2] = '*';
				}	
				
			}
			
			if(board[i][j] == 'n')
			{
				if((i-2)>=0)
				{
					if(j + 1 < SIZE)
						attacked_by_black[i-2][j+1] = '*';
					if(j - 1 >= 0)
						attacked_by_black[i-2][j-1] = '*';
				}
				if(i - 1 >= 0 )
				{
					if(j + 2 < SIZE)
						attacked_by_black[i-1][j+2] = '*';
					if( j - 2 >= 0)
						attacked_by_black[i-1][j-2] = '*';
				}
				if( i + 2 < SIZE)
				{
					if( j + 1 < SIZE)
						attacked_by_black[i+2][j+1]  = '*';
					if(j - 1 >= 0)
						attacked_by_black[i+2][j-1] = '*';
				}
				if( i + 1 < SIZE)
				{
					if(j - 2 >= 0)
						attacked_by_black[i+1][j-2] = '*';
					if(j + 2 < SIZE)
						attacked_by_black[i+1][j+2] = '*';
				}	
				
			}
			if( board[i][j] == 'B')
			{
				REP(k,SIZE)
				{
					if(i - k >= 0 && j + k < SIZE && (board[i-k][j+k] == '.' || board[i-k][j+k] == 'k'))
						attacked_by_white[i-k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j - k >=0 && (board[i+k][j-k]=='.' || board[i+k][j-k] == 'k'))
						attacked_by_white[i+k][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j + k < SIZE && (board[i+k][j+k] == '.' || board[i+k][j+k] == 'k'))
						attacked_by_white[i+k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >=0 && j - k >= 0 && (board[i-k][j-k]=='.' || board[i-k][j-k] =='k'))
						attacked_by_white[i-k][j-k] = '*';
					else
						break;
				}
				

			}
			if( board[i][j] == 'b')
			{
				REP(k,SIZE)
				{
					if(i - k >= 0 && j + k < SIZE && (board[i-k][j+k] == '.' || board[i-k][j+k] == 'K'))
						attacked_by_black[i-k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j - k >=0 && (board[i+k][j-k]=='.' || board[i+k][j-k] == 'K'))
						attacked_by_black[i+k][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j + k <SIZE && (board[i+k][j+k] == '.' || board[i+k][j+k] == 'K'))
						attacked_by_black[i+k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >=0 && j - k >= 0 && (board[i-k][j-k]=='.' || board[i-k][j-k] =='K'))
						attacked_by_black[i-k][j-k] = '*';
					else
						break;
				}
				

			}
			if( board[i][j] == 'R')
			{
				REP(k,SIZE)
				{
					if(j+k < SIZE && (board[i][k+j] == '.' || board[i][k+j] == 'k'))
						attacked_by_white[i][k+j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(j - k >= 0 && (board[i][j-k] =='.' || board[i][j-k] == 'k'))
						attacked_by_white[i][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && (board[i+k][j] == '.' || board[i+k][j] == 'k'))
						attacked_by_white[i+k][j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >= 0 && (board[i-k][j] == '.' || board[i-k][j] == 'k'))
						attacked_by_white[i-k][j] = '*';
					else
						break;
				}
				
			}
			
			if( board[i][j] == 'r')
			{
				REP(k,SIZE)
				{
					if(j+k < SIZE && (board[i][k+j] == '.' || board[i][k+j] == 'K'))
						attacked_by_black[i][k+j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(j - k >= 0 && (board[i][j-k] =='.' || board[i][j-k] == 'K'))
						attacked_by_black[i][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && (board[i+k][j] == '.' || board[i+k][j] == 'K'))
						attacked_by_black[i+k][j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >= 0 && (board[i-k][j] == '.' || board[i-k][j] == 'K'))
						attacked_by_black[i-k][j] = '*';
					else
						break;
				}
				
			}
			if ( board[i][j] == 'Q')
			{
			REP(k,SIZE)
				{
					if(j+k < SIZE && (board[i][k+j] == '.' || board[i][k+j] == 'k'))
						attacked_by_white[i][k+j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(j - k >= 0 && (board[i][j-k] =='.' || board[i][j-k] == 'k'))
						attacked_by_white[i][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && (board[i+k][j] == '.' || board[i+k][j] == 'k'))
						attacked_by_white[i+k][j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >= 0 && (board[i-k][j] == '.' || board[i-k][j] == 'k'))
						attacked_by_white[i-k][j] = '*';
					else
						break;
				}	
				/*bishop*/
				REP(k,SIZE)
				{
					if(i - k >= 0 && j + k < SIZE && (board[i-k][j+k] == '.' || board[i-k][j+k] == 'k'))
						attacked_by_white[i-k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j - k >=0 && (board[i+k][j-k]=='.' || board[i+k][j-k] == 'k'))
						attacked_by_white[i+k][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j + k < SIZE && (board[i+k][j+k] == '.' || board[i+k][j+k] == 'k'))
						attacked_by_white[i+k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >=0 && j - k >= 0 && (board[i-k][j-k]=='.' || board[i-k][j-k] =='k'))
						attacked_by_white[i-k][j-k] = '*';
					else
						break;
				}
				
			}				
		if ( board[i][j] == 'q')
			{
			REP(k,SIZE)
				{
					if(j+k < SIZE && (board[i][k+j] == '.' || board[i][k+j] == 'K'))
						attacked_by_black[i][k+j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(j - k >= 0 && (board[i][j-k] =='.' || board[i][j-k] == 'K'))
						attacked_by_black[i][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && (board[i+k][j] == '.' || board[i+k][j] == 'K'))
						attacked_by_black[i+k][j] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >= 0 && (board[i-k][j] == '.' || board[i-k][j] == 'K'))
						attacked_by_black[i-k][j] = '*';
					else
						break;
				}	
				/*bishop*/
				REP(k,SIZE)
				{
					if(i - k >= 0 && j + k < SIZE && (board[i-k][j+k] == '.' || board[i-k][j+k] == 'K'))
						attacked_by_black[i-k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j - k >=0 && (board[i+k][j-k]=='.' || board[i+k][j-k] == 'K'))
						attacked_by_black[i+k][j-k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i + k < SIZE && j + k <SIZE && (board[i+k][j+k] == '.' || board[i+k][j+k] == 'K'))
						attacked_by_black[i+k][j+k] = '*';
					else
						break;
				}
				REP(k,SIZE)
				{
					if(i - k >=0 && j - k >= 0 && (board[i-k][j-k]=='.' || board[i-k][j-k] =='K'))
						attacked_by_black[i-k][j-k] = '*';
					else
						break;
				}
				
			}
		if( board[i][j] == 'K')
		{
			whiteX = i; whiteY = j;
			for(n = -1; n <= 1; n++)
			{
				for(m = -1; m <=1; m++)
				{
					
						t1 = i + n;
						t2 = j + m;
						if(t1>=0 && t1 < SIZE && t2>=0 && t2 < SIZE)
							attacked_by_white[t1][t2] = '*';
				}
			}
			
		}
		
		if( board[i][j] == 'k')
		{
		       blackX = i; blackY = j;
			for(n = -1; n <= 1; n++)
			{
				for(m = -1; m <=1; m++)
				{
					
						t1 = i + n;
						t2 = j + m;
						if(t1>=0 && t1 < SIZE && t2>=0 && t2 < SIZE)
							attacked_by_black[t1][t2] = '*';
				}
			}
			
		}
	}	
	}
}						
/*The problem is that if we have a figure before the attack. Then it should be discarded. */
