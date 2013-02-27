#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include <math.h>
#include "ppmstruct.h"
char **createGrid(int M, int N); /*Command 'I M N'*/
char **clearGrid(int M, int N, char **grid); /* Command 'C'.*/
void printGrid(char **grid, int M, int N);
char **vertical_segment(char **grid, int x1, int y1, int y2, char c);
char **horizontal_segment(char **grid, int x1, int x2, int y1, char c);
char **rectangle(char **grid, int x1, int y1, int x2, int y2, char c);
int fillRegion(int x, int y, char oldColor, char newColor, char **grid);
int n,m;
int main(void)
{
	char **grid  = NULL;
	char *command[100];
	int i = 0,j = 0,rows=0,columns = 0,k=0,x1 = 0, y1 = 0, y2 = 0,x2 = 0, x = 0, y = 0;
	char c;
	char *digStr = (char *) malloc(sizeof(char) * 20);
	assert(digStr!=NULL);
    command[i]  = (char *) malloc(sizeof(char)* 30);
	while(fgets(command[i], 30, stdin))
	{
		assert(command[i]!=NULL);
		if(command[i][0] == 'X')
			break;
		else if(command[i][0] == 'I') /* Parse the two digits that come after 'I' that is M and N */
			{
				j = 2;
				k = 0;
				for(c = command[i][j]; c >= 48 && c <= 57; c= command[i][++j])
				{
					digStr[k] = c;
					k++;
				}
				rows = atoi(digStr);
				printf("%d\n",rows);
				j++;
				k = 0;
				for(c = command[i][j]; c>=48 && c<= 57; c = command[i][++j])
				{
					digStr[k] = c;
					k++;
				}
				columns = atoi(digStr);
				printf("%d\n",columns);
				n = rows;
				m = columns;
				grid = createGrid(columns,rows);
			}
		else if(command[i][0] == 'C')
		{
			/*the memory should also be freed here*/
			grid = clearGrid(rows,columns,grid);
			assert(grid!=NULL);
		}
		else if(command[i][0] == 'L')
		{
			free(digStr);
			digStr = (char *) malloc(sizeof(char) * 20);
			assert(digStr!=NULL);
			j = 2;
			k = 0;
			for(c = command[i][j]; c>= 48 && c<=57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			x1 = atoi(digStr);
			j++;
			k = 0;
			for(c = command[i][j]; c>= 48 && c<=57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}

			y1 = atoi(digStr);
			j++;
			c = command[i][j];
			grid[y1-1][x1-1] = c;
		}
		else if(command[i][0] == 'P')
			printGrid(grid,rows,columns);
		else if(command[i][0] == 'V')
		{
			free(digStr);
			digStr = (char *) malloc(sizeof(char) * 20);
			assert(digStr!=0);
			j = 2;
			k = 0;
			for(c = command[i][j]; c >= 48 && c<=57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			x1 = atoi(digStr);
			j++;
			k = 0;
			for( c = command[i][j]; c>=48 && c<=57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			y1 = atoi(digStr);
			j++;
			k = 0;
			for( c = command[i][j]; c>= 48 && c<=57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			y2 = atoi(digStr);
			j++;
			c = command[i][j];
			printf("%d %d %d %c\n",x1,x1,y2,c);
			x1 = x1 - 1;
			y1 = y1 - 1;
			y2 = y2 - 1;
			grid= vertical_segment(grid,x1,y1,y2,c);
		}
		else if(command[i][0] == 'H')
		{
			free(digStr);
			digStr = (char *) malloc(sizeof(char) * 20);
			assert(digStr!=NULL);
			k = 0;
			j = 2;
			for(c = command[i][j]; c >= 48 && c <= 57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			x1 = atoi(digStr);
			j++;
			k = 0;
			for(c = command[i][j]; c >= 48 && c <= 57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			x2 = atoi(digStr);
			j++;
			k++;
			for(c = command[i][j]; c >= 48 && c <= 57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			y1 = atoi(digStr);
			j++;
			c = command[i][j];
			printf("%d %d %d %c\n",x1,x2,y1,c);
			x1 = x1 - 1;
			x2 = x2 - 1;
			y1 = y1 - 1;
			grid = horizontal_segment(grid,x1,x2,y1,c);
		}
		else if(command[i][0] == 'K')
		{
			free(digStr);
			digStr = (char *) malloc(sizeof(char) * 20);
			assert(digStr!=NULL);
			j = 2;
			k = 0;
			for(c = command[i][j]; c >= 48 && c <= 57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			x1 = atoi(digStr);
			j++;
			k = 0;
			for(c = command[i][j]; c >= 48 && c <= 57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			y1 = atoi(digStr);
			j++;
			k = 0;
			for(c = command[i][j]; c >= 48 && c<=57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			x2 = atoi(digStr);
			j++;
			k = 0;
			for(c = command[i][j]; c >= 48 && c<=57; c = command[i][++j])
			{
				digStr[k] =  c;
				k++;
			}
			y2 = atoi(digStr);
			j++;
			k = 0;
			c = command[i][j];
			x1 = x1 - 1; y1 = y1 - 1; x2 = x2 - 1; y2 = y2 - 1;
			grid = rectangle(grid,x1,y1,x2,y2,c);
		}
		else if (command[i][0]=='F')
		{
			free(digStr);
			digStr = (char *) malloc(sizeof(char) * 20);
			assert(digStr!=NULL);
			j = 2;
			k = 0;
			for( c = command[i][j]; c >= 48 && c<=57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			x  = atoi(digStr);
			j++;
			k =0;
			for( c = command[i][j]; c >= 48 && c <= 57; c = command[i][++j])
			{
				digStr[k] = c;
				k++;
			}
			y = atoi(digStr);
			j++;
			c = command[i][j];
			printf("%d %d %c\n",x,y,c);
			char oldcolor = grid[y][x];
			if(oldcolor!=c)
			{
			    printf("fucking true");
			    fillRegion(x,y,oldcolor,c,grid);
			}

		}
		else
			continue;
		command[i][strlen(command[i]) - 1]  = '\0'; /* Get rid of the newline*/
		printf("%s\n",command[i]);
		i++;
		command[i] = (char *) malloc(sizeof(char)*30);
	}
	return 0;
}
char **createGrid(int M, int N)
{
	int i,j;
	char **grid;
	grid = (char **) malloc(sizeof(char *) * N);
	assert(grid!=NULL);
	for(i = 0; i < N; i++)
	{
		grid[i] = (char *) malloc(sizeof(char) * M);
		assert(grid[i]!=NULL);
	}
	/*Set all the chars to 'O' */
	for(i = 0; i < N; i++)
	{
		for(j = 0; j < M; j++)
		{
			grid[i][j] = 'O';
		}
	}

	return grid;
}
char **clearGrid(int M, int N,char **grid)
{
	int i,j;
	for(i = 0; i < N; i++)
		for(j = 0; j < M; j++)
			grid[i][j] = 'O';
	return grid;
}
void printGrid(char **grid, int M, int N)
{
	int i,j;
	for(i = 0; i < N; i++)
	{
		for(j = 0; j < M; j++)
			printf("%c",grid[i][j]);
		putchar('\n');
	}
}
char **vertical_segment(char **grid, int x1, int y1, int y2, char c)
{
	int i;
	for(i = y1; i <= y2;i++)
		grid[i][x1] = c;
	return grid;
}
char **horizontal_segment(char **grid,int x1, int x2, int y1, char c)
{
	int i;
	for(i = x1; i<= x2; i++)
	{
		grid[y1][i] = c;
	}
	return grid;
}
char **rectangle(char **grid, int x1, int y1, int x2, int y2, char c)
{
	int i,j;
	for(i = x1; i <= (x2 - x1); i++)
	{
		for(j = y1; j <= (y2 - y1); j++)
		{
			grid[i][j] = c;
		}
	}
	return grid;
}
int fillRegion(int x, int y, char oldColor, char newColor, char **grid) {
	/* (x,y) is in region R */
	printf("where");
	grid[y][x] = newColor;

	/* recursively check all 4 directions for neighbours of (x,y) with same color */
	if ((grid[y][x-1] == oldColor) && (x > 1)) {
		fillRegion(x-1, y, oldColor, newColor, grid);
	}
	if ((grid[y][x+1] == oldColor) && (x < m)) {
		fillRegion(x+1, y, oldColor, newColor, grid);
	}
	if ((grid[y-1][x] == oldColor) && (y > 1)) {
		fillRegion(x, y-1, oldColor, newColor, grid);
	}
	if ((grid[y+1][x] == oldColor) && (y < n)) {
		fillRegion(x, y+1, oldColor, newColor, grid);
	}
	return 0;
}