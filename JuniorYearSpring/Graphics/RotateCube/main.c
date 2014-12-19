//
//  main.c
//  test
//
//  Created by Adam on 2/26/14.
//  Copyright (c) 2014 Adam. All rights reserved.
//


#include <OpenGL/OpenGL.h>
#include <GLUT/GLUT.h>
#include "topsecret.h" 


void display(void)
{
    
    // rotate eye about y
    transform_eye();
    
    // set up new view
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective( 40.0, 1.0, 1.0, 10000.0 );
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    gluLookAt( getEyex(), getEyey(), getEyez(), // eye
              0.0, 0.0, 0.0, // target
              0.0, 1.0, 0.0  // up
              );
    
    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
    draw_triangles();
    //glFlush();
    glutSwapBuffers();
}

int main(int argc, char** argv)
{
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH);
    glutCreateWindow("simple");
    glutDisplayFunc(display);
    glutIdleFunc(glutPostRedisplay);
    glEnable(GL_DEPTH_TEST);
    init_mod();
    glutMainLoop();
}