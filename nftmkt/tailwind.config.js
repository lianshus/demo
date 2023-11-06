/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors:{
        'titlel':'#FF3BFF',
        'titlem':'#ECBFBF',
        'titler':'#5C24FF',
      },
      backgroundImage: {
        'title':'linear-gradient(45deg, #FF3BFF, #ECBFBF, #5C24FF,#D94FD5)'
      },
    },
  },
  plugins: [],
}
