import React, { useState } from 'react';
import StyledInput from '../atom/StyledInput';
import PopupDom from './Popupdom';
import PopupPostCode from './PopupPostCode';

const Address = ({ zipcode, address, setZipcode, setAddress }) => {
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const openPostCode = () => {
    setIsPopupOpen(true);
  };

  const closePostCode = () => {
    setIsPopupOpen(false);
  };

  return (
    <div>
      <StyledInput value={zipcode} readOnly></StyledInput>
      <button type="button" onClick={openPostCode}>
        우편번호 검색
      </button>
      <div>
        <StyledInput value={address} readOnly></StyledInput>
        <StyledInput placeholder="상세주소"></StyledInput>
      </div>
      <div id="popupDom">
        {isPopupOpen && (
          <PopupDom>
            <PopupPostCode
              onClose={closePostCode}
              setZipcode={setZipcode}
              setAddress={setAddress}
            />
          </PopupDom>
        )}
      </div>
    </div>
  );
};

export default Address;
