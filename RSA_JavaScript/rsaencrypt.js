function getPrimes() {
	var p = parseInt(document.rsaform.p.value);
	var q = parseInt(document.rsaform.q.value);
	return [p,q];
}

function calculateN(){
	let p,q;
	[p,q] = getPrimes();
	var n = p * q;
	return n;
}

function calculatePhi(){
	let p,q;
	[p,q] = getPrimes();
	var phi = (p-1) * (q-1);
	return phi;
}

function getEncryptionConstant(){
	var e = parseInt(document.rsaform.e.value);
	return e;
}

function getDecryptionConstant() {
	var phi = calculatePhi();
	var e = getEncryptionConstant();
	var d = (modInverse(e, phi) + phi) % phi;
	document.getElementById('d').value = d;
	return d;
}

function getPublicKey() {
	var e = getEncryptionConstant();
	var n = calculateN();
	return [e, n];
}

function getPrivateKey() {
	var d = getDecryptionConstant();
	var n = calculateN();
	return [d, n];
}

function encrypt() {
	let e, n;
	[e,n] = getPublicKey();
	var M = parseInt(document.rsaform.M.value);
	const C = powerMod(M, e, n);
	//alert("Encrypted Cipher Text="+ C);
	document.getElementById('C').value = C;
	document.getElementById('C2').value = C;
	return C;
}


function decrypt(){
	let d, n;
	[d, n] = getPrivateKey();
	var C = document.getElementById('C2').value;
	const  M = powerMod(C, d, n);
	document.getElementById('M2').value = M;	
}

/*
function modulus(a, b) {
	var ans = ((a % b) + b) % b;
	return ans;
}*/

/*Source: http://umaranis.com/2018/07/12/calculate-modular-exponentiation-powermod-in-javascript-ap-n/*/
function powerMod(base, exponent, modulus) {
    if (modulus === 1) return 0;
    var result = 1;
    base = base % modulus;
    while (exponent > 0) {
        if (exponent % 2 === 1)  //odd number
            result = (result * base) % modulus;
        exponent = exponent >> 1; //divide by 2
        base = (base * base) % modulus;
    }
    return result;
}

function modInverse(b, m) {
	let A1, A2, A3;
	[A1, A2, A3] = [1, 0, m];
	let B1, B2, B3;
	[B1, B2, B3] = [0, 1, b];
	let T1, T2, T3;
	var Q;
	while (B3 != 0 || B3 != 1) {
		if (B3 == 0)
			return 0;
		if (B3 == 1)
			return B2;
		Q = Math.floor(A3 / B3);
		[T1, T2, T3] = [A1 - Q * B1, A2 - Q * B2, A3 - Q * B3];
		[A1, A2, A3] = [B1, B2, B3];
		[B1, B2, B3] = [T1, T2, T3];
		}
		return 0;
}
