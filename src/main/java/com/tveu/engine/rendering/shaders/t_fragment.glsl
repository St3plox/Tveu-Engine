#version 330 core

out vec4 FragColor; // Output color to the framebuffer
in vec3 interpolatedColor; // Input interpolated color from the vertex shader\n" +

void main()
{
    FragColor = vec4(interpolatedColor, 1.0); // Set the fragment color to the interpolated color\n" +
}