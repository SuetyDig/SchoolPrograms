#include <GLUT/GLUT.h>
#include "raytracer.h"


//eye is .5 .5 -2
//Screen points corrispond to 0 -> 1, 0 -> 1 and 0
//Triangle is defined as (1,0,6) (.5,1,6) and (0,0,6)

void display(void)
{
    //Ray tracer assumes resolution is square
    draw(500);
 	glFlush();
}

int main(int argc, char** argv)
{
    //Sets the eye location
    eye.x = .5;
    eye.y = .5;
    eye.z = -1;
    
    //Points on the ground
    vector p1 = {0, 0, 0};
    vector p2 = {0, 0, 1};
    vector p3 = {1, 0, 1};
    vector p4 = {1, 0, 0};
    
    //Makes the ground
    makeRect(p1, p2, p3, p4, 0);
    
    //Makes the cube
    vector center = {.2, .15, .6};
    makeCube(center, .1, 0);
    
    //Makes the light source
    vector mid = {.5, .5, 1.5};
    makeSphereLight(mid, .1);
    
    
    
    //Sets the ambient lighting
    ambient = .1;
    
    glutInit(&argc,argv);
	glutCreateWindow("simple"); 
	glutDisplayFunc(display);
	glutMainLoop();
}
