import ReactDom from 'react-dom';

const PopupDom = ({ children }) => {
  const el = document.getElementById('popupDom');
  return ReactDom.createPortal(children, el);
};

export default PopupDom;
