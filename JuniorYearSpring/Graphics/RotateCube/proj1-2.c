//Tom Esposito
//Collaborated with Sean Daly

#include <GLUT/glut.h>
#include <math.h>
#include "devil.c"

void display();


//struct for location of viewer.
typedef struct{
	float eyex;
	float eyey;
	float eyez;
	float theta;
} Viewer;

Viewer view;

int main(int argc, char** argv){
	view.eyex = 5.0;
	view.eyey = 5.0;
	view.eyez = 5.0;
	view.theta = 0.01;

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH);
	glutCreateWindow("texture");
	glutDisplayFunc(display);
	glutIdleFunc(glutPostRedisplay);
	glEnable(GL_DEPTH_TEST);
	glutMainLoop();

	
}

void display(void){
	
	Viewer *ptr;
	ptr = &view;
	//func to rotate about the y-axis.
	transform_eye(ptr);

	//set up new view.
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(40.0, 1.0, 1.0, 10000.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(view.eyex, view.eyey, view.eyez, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	draw_triangles();
	glutSwapBuffers();
}

void transform_eye(Viewer *view){
	float theta = view->theta;
	float eyex = view->eyex;
	float eyez = view->eyez;
	float x = (eyex * cos(theta))+(eyez * sin(theta)); 
	float z = (eyex * -sin(theta))+(eyez * cos(theta));
	view->eyex = x;
	view->eyez = z;	
}

void draw_triangles(){





	float a[3] = {-0.5, 0.5, 0.5};
	float b[3] = {-0.5, 0.5, -0.5};
	float c[3] = {0.5, 0.5, -0.5};
	float d[3] = {0.5, 0.5, 0.5};
	float e[3] = {-0.5, -0.5, 0.5};
	float f[3] = {-0.5, -0.5, -0.5};
	float g[3] = {0.5, -0.5, -0.5};
	float h[3] = {0.5, -0.5, 0.5};


glEnable(GL_TEXTURE_2D);

glTexImage2D(GL_TEXTURE_2D,0,gimp_image.bytes_per_pixel,gimp_image.width,gimp_image.height,0,GL_RGB,GL_UNSIGNED_BYTE,gimp_image.pixel_data);
glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);
glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);



	glBegin(GL_TRIANGLES);
		//Front face

		glTexCoord2f(0.0,0.0);
		glVertex3f(a[0],a[1],a[2]);
		glTexCoord2f(0.0,1.0);
		glVertex3f(b[0],b[1],b[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(c[0],c[1],c[2]);

		glTexCoord2f(0.0,0.0);
		glVertex3f(a[0],a[1],a[2]);
		glTexCoord2f(1.0,0.0);
		glVertex3f(d[0],d[1],d[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(c[0],c[1],c[2]);

		//Top face

		glTexCoord2f(0.0,0.0);
		glVertex3f(e[0],e[1],e[2]);
		glTexCoord2f(0.0,1.0);
		glVertex3f(a[0],a[1],a[2]);
		glTexCoord2f(1.0,0.0);
		glVertex3f(d[0],d[1],d[2]);

		glTexCoord2f(0.0,0.0);
		glVertex3f(e[0],e[1],e[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(h[0],h[1],h[2]);
		glTexCoord2f(1.0,0.0);
		glVertex3f(d[0],d[1],d[2]);

		//Back face

		glTexCoord2f(0.0,0.0);
		glVertex3f(e[0],e[1],e[2]);
		glTexCoord2f(0.0,1.0);
		glVertex3f(f[0],f[1],f[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(g[0],g[1],g[2]);

		glTexCoord2f(0.0,0.0);
		glVertex3f(e[0],e[1],e[2]);
		glTexCoord2f(1.0,0.0);
		glVertex3f(h[0],h[1],h[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(g[0],g[1],g[2]);

		//Bottom face

		glTexCoord2f(0.0,1.0);
		glVertex3f(f[0],f[1],f[2]);
		glTexCoord2f(0.0,0.0);
		glVertex3f(b[0],b[1],b[2]);
		glTexCoord2f(1.0,0.0);
		glVertex3f(c[0],c[1],c[2]);

		glTexCoord2f(0.0,1.0);
		glVertex3f(f[0],f[1],f[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(g[0],g[1],g[2]);
		glTexCoord2f(1.0,0.0);
		glVertex3f(c[0],c[1],c[2]);

		//Left face

		glTexCoord2f(1.0,0.0);
		glVertex3f(a[0],a[1],a[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(b[0],b[1],b[2]);
		glTexCoord2f(0.0,1.0);
		glVertex3f(f[0],f[1],f[2]);

		glTexCoord2f(1.0,0.0);
		glVertex3f(a[0],a[1],a[2]);
		glTexCoord2f(0.0,0.0);
		glVertex3f(e[0],e[1],e[2]);
		glTexCoord2f(0.0,1.0);
		glVertex3f(f[0],f[1],f[2]);

		//Right face

		glTexCoord2f(1.0,0.0);
		glVertex3f(d[0],d[1],d[2]);
		glTexCoord2f(1.0,1.0);
		glVertex3f(c[0],c[1],c[2]);
		glTexCoord2f(0.0,1.0);
		glVertex3f(g[0],g[1],g[2]);

		glTexCoord2f(1.0,0.0);
		glVertex3f(d[0],d[1],d[2]);
		glTexCoord2f(0.0,0.0);
		glVertex3f(h[0],h[1],h[2]);
		glTexCoord2f(0.0,1.0);
		glVertex3f(g[0],g[1],g[2]);

	glEnd();	
}

