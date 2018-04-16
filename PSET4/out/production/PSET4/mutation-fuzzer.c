#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <stdio.h>
#include <string.h>

// Flipping a random bit of a random character in the input string
void flip(char* input) {
	int flip_position, flip_bit;

	srand(time(NULL));
	// choose a random position in the input string
	flip_position = rand() % (strlen(input) - 1);
	
	// choose a random bit in the respective character, why it is 7 and not 8?
	flip_bit = rand() % 7;

	printf("Flipping bit %d in position %d.....\n", flip_bit, flip_position);

	// flip the bit in-place
	input[flip_position] = input[flip_position] ^ (1 << flip_bit);

	printf("Mutated input = %s\n", input);
	
}


// Trimming an input string at a randomly chosen position
void trim(char* input) {
	int trim_position = 0;
	char* mutated_input;

	srand(time(NULL));
	trim_position = rand() % (strlen(input) - 1);	
	printf("Trimming at position %d.....\n", trim_position);
	
	mutated_input = (char *) malloc ((trim_position + 1) * sizeof(char));
	strncpy(mutated_input, input, trim_position);
	mutated_input[trim_position] = '\0';

	printf("Mutated input = %s\n", mutated_input);
}

int main() {
	char existing_input[128]="ISTDisApillarInSUTDbutItsNameiSGoingToChange"; 
	char* input;
	int index;

	printf("Original input = %s\n", existing_input);
	//trim(existing_input);
	flip(existing_input);
}
