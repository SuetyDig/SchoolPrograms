//
//  topsecret.c
//  test
//
//  Created by Adam on 2/26/14.
//  Copyright (c) 2014 Adam. All rights reserved.
//


#include "topsecret.h"

GLfloat faceHolder[4][3];
GLfloat face0[4][3];
GLfloat face1[4][3];
GLfloat face2[4][3];
GLfloat face3[4][3];
GLfloat face4[4][3];
GLfloat face5[4][3];

GLfloat pos1[3];
GLfloat pos2[3];
GLfloat pos3[3];
GLfloat pos4[3];
GLfloat pos5[3];
GLfloat pos6[3];
GLfloat pos7[3];
GLfloat pos8[3];

GLfloat eyex;
GLfloat eyez;
GLfloat eyey;

void transform_eye()
{
    float angleSin = 0.01308959557;
    float angleCos = 0.99991432757;

    GLfloat eyeXHolder = eyex;
    GLfloat eyeZHolder = eyez;
    
    //Formula for x portion of the matrix multiplication
    eyex = (eyeXHolder*angleCos)+(angleSin*eyeZHolder);
    
    //Formula for x portion of the matrix multiplication
    eyez = (eyeXHolder*(-angleSin))+(angleCos*eyeZHolder);
}

void init_mod()
{
    eyez = eyey = eyex = 5;
    
    //Set the points
    
    //Point 1
    pos1[0] = -1.0;
    pos1[1] = -1.0;
    pos1[2] = -1.0;
    
    //Point 2
    pos2[0] = -1.0;
    pos2[1] = -1.0;
    pos2[2] = 1.0;
    
    //Point 3
    pos3[0] = 1.0;
    pos3[1] = -1.0;
    pos3[2] = 1.0;
    
    //Point 4
    pos4[0] = 1.0;
    pos4[1] = -1.0;
    pos4[2] = -1.0;
    
    //Point 5
    pos5[0] = 1.0;
    pos5[1] = 1.0;
    pos5[2] = 1.0;
    
    //Point 6
    pos6[0] = 1.0;
    pos6[1] = 1.0;
    pos6[2] = -1.0;
    
    //Point 7
    pos7[0] = -1.0;
    pos7[1] = 1.0;
    pos7[2] = 1.0;
    
    //Point 8
    pos8[0] = -1.0;
    pos8[1] = 1.0;
    pos8[2] = -1.0;
    
    //Place points in faces
    
    //Front face points
    makeFace(face0, pos1, pos6, pos4, pos8);
 
    //Right face points
    makeFace(face1, pos4, pos5, pos3, pos6);

    //Top face points
    makeFace(face2, pos8, pos5, pos6, pos7);
    
    //Left face points
    makeFace(face3, pos1, pos7, pos2, pos8);

    //Back face points
    makeFace(face4, pos3, pos7, pos2, pos5);
    
    //Bottom face points
    makeFace(face5, pos1, pos3, pos4, pos2);

}

void draw_triangles()
{
    glEnable(GL_TEXTURE_2D);
    glTexImage2D(GL_TEXTURE_2D, 0, 3, gimp_image.width, gimp_image.height, 0, GL_RGB, GL_UNSIGNED_BYTE, gimp_image.pixel_data);
    
    
//    gluBuild2DMipmaps(GL_TEXTURE_2D, 3, gimp_image.width, gimp_image.height, GL_RGB, GL_UNSIGNED_BYTE, gimp_image.pixel_data);
    
//    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

    glBegin(GL_TRIANGLES);
    
    //Front Face
    glTexCoord2f(0, 1); glVertex3f(face0[0][0], face0[0][1], face0[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face0[1][0], face0[1][1], face0[1][2]);
    glTexCoord2f(1, 1); glVertex3f(face0[2][0], face0[2][1], face0[2][2]);
    
    glTexCoord2f(0, 1); glVertex3f(face0[0][0], face0[0][1], face0[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face0[1][0], face0[1][1], face0[1][2]);
    glTexCoord2f(0, 0); glVertex3f(face0[3][0], face0[3][1], face0[3][2]);
    

    //Right Face
    glTexCoord2f(0, 1); glVertex3f(face1[0][0], face1[0][1], face1[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face1[1][0], face1[1][1], face1[1][2]);
    glTexCoord2f(1, 1); glVertex3f(face1[2][0], face1[2][1], face1[2][2]);
    
    glTexCoord2f(0, 1); glVertex3f(face1[0][0], face1[0][1], face1[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face1[1][0], face1[1][1], face1[1][2]);
    glTexCoord2f(0, 0); glVertex3f(face1[3][0], face1[3][1], face1[3][2]);
 
    //Top Face
    glTexCoord2f(0, 1); glVertex3f(face2[0][0], face2[0][1], face2[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face2[1][0], face2[1][1], face2[1][2]);
    glTexCoord2f(1, 1); glVertex3f(face2[2][0], face2[2][1], face2[2][2]);
    
    glTexCoord2f(0, 1); glVertex3f(face2[0][0], face2[0][1], face2[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face2[1][0], face2[1][1], face2[1][2]);
    glTexCoord2f(0, 0); glVertex3f(face2[3][0], face2[3][1], face2[3][2]);
    
    //Left Face
    glTexCoord2f(0, 1); glVertex3f(face3[0][0], face3[0][1], face3[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face3[1][0], face3[1][1], face3[1][2]);
    glTexCoord2f(1, 1); glVertex3f(face3[2][0], face3[2][1], face3[2][2]);
    
    glTexCoord2f(0, 1); glVertex3f(face3[0][0], face3[0][1], face3[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face3[1][0], face3[1][1], face3[1][2]);
    glTexCoord2f(0, 0); glVertex3f(face3[3][0], face3[3][1], face3[3][2]);
    
    
    //Back Face
    glTexCoord2f(0, 1); glVertex3f(face4[0][0], face4[0][1], face4[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face4[1][0], face4[1][1], face4[1][2]);
    glTexCoord2f(1, 1); glVertex3f(face4[2][0], face4[2][1], face4[2][2]);
    
    glTexCoord2f(0, 1); glVertex3f(face4[0][0], face4[0][1], face4[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face4[1][0], face4[1][1], face4[1][2]);
    glTexCoord2f(0, 0); glVertex3f(face4[3][0], face4[3][1], face4[3][2]);
    
    //Bottom Face
    glTexCoord2f(0, 1); glVertex3f(face5[0][0], face5[0][1], face5[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face5[1][0], face5[1][1], face5[1][2]);
    glTexCoord2f(1, 1); glVertex3f(face5[2][0], face5[2][1], face5[2][2]);
    
    glTexCoord2f(0, 1); glVertex3f(face5[0][0], face5[0][1], face5[0][2]);
    glTexCoord2f(1, 0); glVertex3f(face5[1][0], face5[1][1], face5[1][2]);
    glTexCoord2f(0, 0); glVertex3f(face5[3][0], face5[3][1], face5[3][2]);
    glEnd();
    
}

void makeFace(GLfloat face[4][3],
               GLfloat point0[3], GLfloat point1[3], GLfloat point2[3], GLfloat point3[3])
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if      (i == 0) face[i][j] = point0[j];
            else if (i == 1) face[i][j] = point1[j];
            else if (i == 2) face[i][j] = point2[j];
            else if (i == 3) face[i][j] = point3[j];
        }
    }
}

GLfloat getEyex()
{
    return eyex;
}

GLfloat getEyey()
{
    return eyey;
}

GLfloat getEyez()
{
    return eyez;
}