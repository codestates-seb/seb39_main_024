/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{html,js,jsx,ts,tsx}'],
  theme: {
    extend: {
      colors: {
        pink: '#FFDADA',
        green: '#A9F1DF',
        gray: '#F4F4F4',
      },
      width: {
        768: '768px',
      },
      height: {
        768: '768px',
      },
    },
    maxWidth: {
      480: '480px',
    },
    screens: {
      sm: { max: '767px' },
      // => @media (max: '767px') { ... }
      md: { min: '768px' },
    },
  },
  plugins: [],
};
