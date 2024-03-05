# include<stdio.h>
# include<string.h>
# include<stdlib.h>

typedef struct Node {
	int coef;
	int expo;
	struct Node* next;
}Node;
typedef Node* polynomial;

void zero(Node* p) {
	if (p != NULL)
	{
		p->coef = 0;
		p->expo = 0;
		p->next = NULL;
	}
}
Node* creat_head(void) {
	Node* head = (Node*)malloc(sizeof(Node));
	zero(head);
	return head;
}
void creat_node(Node* ptr, int c, int e) {
	Node* temp = (Node*)malloc(sizeof(Node));
	temp->coef = c;
	temp->expo = e;
	temp->next = NULL;
	ptr->next = temp;
}
Node* finder(Node* head, int expo) {
	Node* cur = head;
	while (cur != NULL)
	{
		if (cur->expo == expo)
		{
			return cur;
		}
		cur = cur->next;
	}
	return NULL;
}

polynomial add(polynomial a, polynomial b) {
	polynomial result = creat_head();
	Node* ptr = result;
	Node* dc1 = a;
	Node* dc2 = b;

	while (dc1 != NULL && dc2 != NULL)
	{
		if (dc1->expo == dc2->expo)
		{
			Node* temp = (Node*)malloc(sizeof(Node));
			zero(temp);
			temp->coef = dc1->coef + dc2->coef;
			temp->expo = dc1->expo;
			ptr->next = temp;
			ptr = ptr->next;
			dc1 = dc1->next;
			dc2 = dc2->next;
		}
		else if (dc1->expo > dc2->expo)
		{
			Node* temp = (Node*)malloc(sizeof(Node));
			zero(temp);
			temp->coef = dc1->coef;
			temp->expo = dc1->expo;
			ptr->next = temp;
			ptr = ptr->next;
			dc1 = dc1->next;
		}
		else
		{
			Node* temp = (Node*)malloc(sizeof(Node));
			zero(temp);
			temp->coef = dc2->coef;
			temp->expo = dc2->expo;
			ptr->next = temp;
			ptr = ptr->next;
			dc2 = dc2->next;
		}
	}

	while (dc1 != NULL)
	{
		Node* temp = (Node*)malloc(sizeof(Node));
		zero(temp);
		temp->coef = dc1->coef;
		temp->expo = dc1->expo;
		ptr->next = temp;
		ptr = ptr->next;
		dc1 = dc1->next;
	}

	while (dc2 != NULL)
	{
		Node* temp = (Node*)malloc(sizeof(Node));
		zero(temp);
		temp->coef = dc2->coef;
		temp->expo = dc2->expo;
		ptr->next = temp;
		ptr = ptr->next;
		dc2 = dc2->next;
	}

	return result;
}


Node* find_tail(polynomial a)
{
	Node* cur = a;
	while (cur->next != NULL)
	{
		cur = cur->next;
	}
	return cur;
}
void computer(polynomial result, int c, int e)
{
	if (finder(result, e) == NULL)
	{
		Node* temp = (Node*)malloc(sizeof(Node));
		temp->coef = c;
		temp->expo = e;
		temp->next = NULL;
		find_tail(result)->next = temp;
		return;
	}
	else
	{
		finder(result, e)->coef += c;
		if (finder(result, e)->coef < 10)
		{
			return;
		}
		else
		{
			int j = finder(result, e)->coef / 10;
			finder(result, e)->coef = finder(result, e)->coef % 10;
			computer(result, j, e + 1);
		}
	}
}

polynomial multiply(polynomial a, polynomial b)
{
	polynomial result = creat_head();
	Node* ptr = result;
	Node* dc1 = a;
	while (dc1 != NULL)
	{
		Node* dc2 = b;
		while (dc2 != NULL)
		{
			int temp = 0;
			int c = dc1->coef * dc2->coef;
			int j = c / 10;
			if (j >= 1)
			{
				temp = 1;
			}
			int e = dc1->expo + dc2->expo + temp;
			computer(result, c % 10, e - temp);
			computer(result, j, e);
			dc2 = dc2->next;
		}
		dc1 = dc1->next;
	}
	return result;
}


int main(void) {
	char num_1[] = "31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
	char num_2[] = "27182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274";
	char num_m[] = "101010101010101010101010101010101010101";
	polynomial a = creat_head();
	polynomial b = creat_head();
	polynomial m = creat_head();
	polynomial cst = creat_head();
	Node* ptr_1 = a;
	Node* ptr_2 = b;
	Node* ptr_m = m;
	char* dc1 = num_1;
	char* dc2 = num_2;
	char* cur_m = num_m;
	int count_1 = strlen(num_1);
	int count_2 = strlen(num_2);
	int count_m = strlen(num_m);

	while (count_1 != 0)
	{
		count_1 -= 1;
		Node* temp = (Node*)malloc(sizeof(Node));
		zero(temp);
		temp->coef = int(*dc1) - int('0');
		temp->expo = count_1;
		ptr_1->next = temp;
		ptr_1 = ptr_1->next;
		dc1 += 1;
	}
	while (count_2 != 0)
	{
		count_2 -= 1;
		Node* temp = (Node*)malloc(sizeof(Node));
		zero(temp);
		temp->coef = int(*dc2) - int('0');
		temp->expo = count_2;
		ptr_2->next = temp;
		ptr_2 = ptr_2->next;
		dc2 += 1;
	}
	while (count_m != 0)
	{
		count_m -= 1;
		Node* temp = (Node*)malloc(sizeof(Node));
		zero(temp);
		temp->coef = int(*cur_m) - int('0');
		temp->expo = count_m;
		ptr_m->next = temp;
		ptr_m = ptr_m->next;
		cur_m += 1;
	}
	Node* temp_1 = (Node*)malloc(sizeof(Node));
	Node* temp_2 = (Node*)malloc(sizeof(Node));
	Node* temp_3 = (Node*)malloc(sizeof(Node));

	zero(temp_1);
	zero(temp_2);
	zero(temp_3);

	temp_1->coef = 1;
	temp_1->expo = 22;
	temp_2->coef = 1;
	temp_2->expo = 8;
	temp_3->coef = 1;
	cst->next = temp_1;
	temp_1->next = temp_2;
	temp_2->next = temp_3;

	polynomial result_mul = multiply(a, b);
	polynomial result_m = multiply(m, m);
	polynomial n = multiply(result_m, cst);

	Node* p_2 = result_mul;
	Node* p_n = n;
	Node* p_m = result_m;

	
	printf("The resultult of m:\n");
	while (p_m != NULL)
	{
		if (p_m->coef == 0)
		{
			p_m = p_m->next;
			continue;
		}
		else
		{
			printf("coef: %d, expo: %d\n", p_m->coef, p_m->expo);
			p_m = p_m->next;
		}
	}

	printf("\nThe resultult of q:\n");
	while (p_2 != NULL)
	{
		if (p_2->coef == 0)
		{
			p_2 = p_2->next;
			continue;
		}
		printf("coef: %d, expo: %d\n", p_2->coef, p_2->expo);
		p_2 = p_2->next;
	}

	printf("\nThe resultult of n:\n");
	while (p_n != NULL)
	{
		if (p_n->coef == 0)
		{
			p_n = p_n->next;
			continue;
		}
		printf("coef: %d, expo: %d\n", p_n->coef, p_n->expo);
		p_n = p_n->next;
	}
	return 0;
}
