package com.student.system.service;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IpfsService {
	static IPFS ipfs = new IPFS("/ip4/192.168.100.100/tcp/5001");
	public String addRecode(String recode) throws IOException {
		NamedStreamable.ByteArrayWrapper byteArray=new NamedStreamable.ByteArrayWrapper(recode.getBytes());
		MerkleNode addResult = ipfs.add(byteArray).get(0);
		return addResult.hash.toString();
	}
	public String selectRecode(String hash) throws IOException {
		Multihash filePointer = Multihash.fromBase58(hash);
		byte[] data = ipfs.cat(filePointer);
		String str=new String(data);
		return str;
	}
}