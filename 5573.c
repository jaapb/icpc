#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char letter[26];
int preocc[26];
int *occ;
int letters;

int gcd (int x, int y)
{
	int t;
	while (x % y)
	{
	  t = x;
		x = y;
		y = t % y;
	}
	return y;
}

int let_comp (const void *a, const void *b)
{
	char l1 = *((char *) a);
	char l2 = *((char *) b);
	if (l1 < l2) return -1;
	if (l1 > l2) return 1;
	return 0;
}

int *remove_and_alloc (int *p, int p_len, int l)
{
	int *p_res = (int *) malloc (p_len * sizeof (int));
	memcpy (p_res, p, p_len * sizeof (int));
	p_res [l] -= 1;
	return p_res;
}

long long permutations (int *p, int p_len)
{
	long long result = 1;
	int sum = 0, i, j, k, f;
	int *factors;
	for (i = 0; i < p_len; i++)
		sum += p [i];
	/* fprintf (stderr, "sum: %d\n", sum); */
	factors = (int *) malloc ((sum + 1) * sizeof (int));	
	for (i = 2; i <= sum; i++)
		factors [i] = i;
	for (i = 0; i < p_len; i++)
		for (j = 2; j <= p [i]; j++)
		{
			f = j;
			/* fprintf (stderr, "dividing out %d from %d\n", j, p [i]); */
			for (k = 2; k <= sum && f > 1; k++)
			{
				int g = gcd (f, factors [k]);
				f /= g;
				/* fprintf (stderr, "factor %d(%d): gcd %d, remaining div %d", k, factors [k], g, f); */
				factors [k] /= g;
				/* fprintf (stderr, ", remaining factor %d\n", factors [k]); */
			}
		}
	for (i = 2; i <= sum; i++)
		result *= factors [i];
	/* fprintf (stderr, "result: %lld", result); */
	return result;
}

int main (int argc, char **argv)
{
	char l[31];
	int i, j, k, size;
	long long pos;
	do
	{
		fscanf (stdin, "%30s\n", &l);
		if (l [0] == '#')
			return 0;
		letters = 0;
		size = strlen (l);
		occ = (int *) malloc (26 * sizeof (int));
		for (i = 0; i < 26; i++)
			preocc [i] = 0;
		for (i = 0; i < size; i++)
			preocc [l [i] - 'a'] += 1;
		for (i = 0; i < 26; i++)
			if (preocc [i] > 0)
			{
				letter [i] = letters;
				occ [letters++] = preocc [i];
			}
		/* for (i = 0; i < letters; i++)
			fprintf (stderr, "%d [%c] %d\n", i, i + 'a', occ [i]); */
		/* fprintf (stderr, "occ: ");
		for (int k = 0; k < letters; k++)
			fprintf (stderr, " %d", occ [k]);
		fprintf (stderr, "\n");	 */
		pos = 0;
		for (i = 0; i < size; i++)
		{
			int *t;
			for (j = 0; j < letter [l [i] - 'a']; j++)
			{
				/* fprintf (stderr, "i: %d j: %d l [i]: %c (%d)\n", i, j, l[i], letter[l[i]-'a']); */
				if (occ [j] > 0)
				{
					int *occ2 = remove_and_alloc (occ, letters, j);
					/* fprintf (stderr, "occ2:");
					for (k = 0; k < letters; k++)
						fprintf (stderr, " %d", occ2 [k]);
					fprintf (stderr, "\n"); */
					pos += permutations (occ2, letters);
					/* fprintf (stderr, "pos: %d\n", pos); */
					free (occ2);
				}
			}
			t = remove_and_alloc (occ, letters, letter [l [i] - 'a']);
			free (occ);
			occ = t;
			/* fprintf (stderr, "new occ:");
			for (k = 0; k < letters; k++)
				fprintf (stderr, " %d", occ [k]);
			fprintf (stderr, "\n"); */
		}
		fprintf (stdout, "%10lld\n", pos + 1);
	}		
	while (-1);
	return 0;
}
